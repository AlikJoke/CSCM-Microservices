package ru.project.cscm.dao.monitoring;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import ru.project.cscm.dao.core.AbstractService;
import ru.project.cscm.dao.core.mappers.MonitoringFilterMapper;
import ru.project.cscm.dao.core.mappers.UserMapper;
import ru.project.cscm.dto.items.filters.MonitoringFilter;

@Service
public class MonitoringFilterService extends
		AbstractService<MonitoringFilterMapper> {

	@Autowired
	private AtmService atmService;

	public List<MonitoringFilter> getFilters() {
		return this.getMapper().getFiltersByUser(
				SecurityContextHolder.getContext().getAuthentication()
						.getName());
	}

	public void deleteFilter(Integer id) {
		this.getMapper().deleteFilter(id);

		this.threadContext.getSession().commit();
	}

	public void save(MonitoringFilter filter) {
		final String login = SecurityContextHolder.getContext()
				.getAuthentication().getName();

		if (filter.getId() == null) {
			this.getMapper().createFilter(
					this.threadContext.getMapper(UserMapper.class, false)
							.getUserIdByLogin(login),
					this.getMapper().getLastFilterId() + 1,
					filter.getName(),
					filter.getDescription(),
					StringUtils.collectionToDelimitedString(
							filter.getStates().stream().map(s -> s.getId())
									.collect(Collectors.toList()), ";"),
					StringUtils.collectionToDelimitedString(filter
							.getMalfunctions().stream().map(s -> s.getId())
							.collect(Collectors.toList()), ";"),
					filter.getDaysToCashEnd(),
					StringUtils.collectionToDelimitedString(
							filter.getCashState(), ";"),
					filter.getDeviceType(),
					StringUtils.collectionToDelimitedString(
							filter.getEncashmentState(), ";"));
		} else {
			this.getMapper().updateFilter(
					filter.getId(),
					filter.getName(),
					filter.getDescription(),
					StringUtils.collectionToDelimitedString(
							filter.getStates().stream().map(s -> s.getId())
									.collect(Collectors.toList()), ";"),
					StringUtils.collectionToDelimitedString(filter
							.getMalfunctions().stream().map(s -> s.getId())
							.collect(Collectors.toList()), ";"),
					filter.getDaysToCashEnd(),
					StringUtils.collectionToDelimitedString(
							filter.getCashState(), ";"),
					filter.getDeviceType(),
					StringUtils.collectionToDelimitedString(
							filter.getEncashmentState(), ";"));
		}

		this.threadContext.getSession().commit();
	}

	@Override
	protected Class<MonitoringFilterMapper> getMapperClass() {
		return MonitoringFilterMapper.class;
	}

}
