package ru.project.cscm.dao.monitoring;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import ru.project.cscm.dao.core.AbstractService;
import ru.project.cscm.dao.core.mappers.AtmMapper;
import ru.project.cscm.dto.items.common.Atm;
import ru.project.cscm.dto.items.common.AtmGroup;
import ru.project.cscm.dto.items.common.Currency;

@Service
public class AtmService extends AbstractService<AtmMapper> {

	public List<Atm> getAtmsByAtmGroup(@NotNull Integer groupId) {
		return this.getMapper().getAtmsByAtmGroup(groupId);
	}

	public List<Atm> getAtmsNotInAtmGroup(@NotNull Integer groupId) {
		return this.getMapper().getAtmsNotInAtmGroup(groupId);
	}
	
	public List<Atm> getAtmsNotInGroup() {
		final List<Atm> atms = this.getMapper().getAtmsNotInGroup();
		atms.forEach(a -> {
			a.setMainCurrency(this.getMapper().getMainCurrencyByAtm(a.getId()));
			a.setSecondaryCurrency(this.getMapper().getSecCurrencyByAtm(
					a.getId()));
			a.setSecondaryCurrency2(this.getMapper().getSec2CurrencyByAtm(
					a.getId()));
			a.setSecondaryCurrency3(this.getMapper().getSec3CurrencyByAtm(
					a.getId()));
		});
		
		return atms;
	}

	@Override
	protected Class<AtmMapper> getMapperClass() {
		return AtmMapper.class;
	}

	public List<Currency> getCurrencies() {
		return this.getMapper().getCurrencies();
	}

	public void deleteAtm(Integer atmId) {
		this.getMapper().deleteAtm(atmId);

		this.threadContext.getSession().commit();
	}

	public void save(Atm atm) {
		final Integer mainCurrCode = Optional.ofNullable(atm.getMainCurrency())
				.map(c -> c.getId()).orElse(null);
		final Integer secCurrCode = Optional
				.ofNullable(atm.getSecondaryCurrency()).map(c -> c.getId())
				.orElse(null);
		final Integer sec2CurrCode = Optional
				.ofNullable(atm.getSecondaryCurrency2()).map(c -> c.getId())
				.orElse(null);
		final Integer sec3CurrCode = Optional
				.ofNullable(atm.getSecondaryCurrency3()).map(c -> c.getId())
				.orElse(null);

		if (this.getMapper().getAtmById(atm.getId()) == null) {
			this.getMapper().createAtm(atm.getId(), atm.getName(),
					atm.getStreet(), atm.getCity(), atm.getState(),
					atm.getInstituteId(), atm.getExtAtmId(), mainCurrCode,
					secCurrCode, sec2CurrCode, sec3CurrCode, atm.getModel());
		} else {
			this.getMapper().updateAtm(atm.getId(), atm.getName(),
					atm.getStreet(), atm.getCity(), atm.getState(),
					atm.getInstituteId(), atm.getExtAtmId(), mainCurrCode,
					secCurrCode, sec2CurrCode, sec3CurrCode, atm.getModel());
		}

		this.threadContext.getSession().commit();
	}

	public List<Atm> getAtms() {
		final List<Atm> atms = this.getReadOnlyMapper().getAtms();
		atms.forEach(a -> {
			a.setMainCurrency(this.getReadOnlyMapper().getMainCurrencyByAtm(a.getId()));
			a.setSecondaryCurrency(this.getReadOnlyMapper().getSecCurrencyByAtm(
					a.getId()));
			a.setSecondaryCurrency2(this.getReadOnlyMapper().getSec2CurrencyByAtm(
					a.getId()));
			a.setSecondaryCurrency3(this.getReadOnlyMapper().getSec3CurrencyByAtm(
					a.getId()));
		});

		return atms;
	}

	public Atm getAtmById(Integer atmId) {
		final Atm a = this.getReadOnlyMapper().getAtmById(atmId);
		a.setMainCurrency(this.getReadOnlyMapper().getMainCurrencyByAtm(a.getId()));
		a.setSecondaryCurrency(this.getReadOnlyMapper().getSecCurrencyByAtm(a.getId()));
		a.setSecondaryCurrency2(this.getReadOnlyMapper()
				.getSec2CurrencyByAtm(a.getId()));
		a.setSecondaryCurrency3(this.getReadOnlyMapper()
				.getSec3CurrencyByAtm(a.getId()));

		return a;
	}
	
	public Atm getAtmByExtId(String atmId) {
        final Atm a = this.getReadOnlyMapper().getAtmByExtId(atmId);
        return a;
    }

	public List<AtmGroup> getAtmGroupsById(Integer atmId) {
		return this.getReadOnlyMapper().getAtmGroupsByAtmId(atmId);
	}
	
	public List<Atm> getAtmsByFilter(List<Integer> groupsIds, List<Integer> atmsIds) {
		final List<Atm> atms = this.getReadOnlyMapper().getAtmsByFilter(groupsIds, atmsIds);
		atms.forEach(a -> {
			a.setMainCurrency(this.getReadOnlyMapper().getMainCurrencyByAtm(a.getId()));
			a.setSecondaryCurrency(this.getReadOnlyMapper().getSecCurrencyByAtm(
					a.getId()));
			a.setSecondaryCurrency2(this.getReadOnlyMapper().getSec2CurrencyByAtm(
					a.getId()));
			a.setSecondaryCurrency3(this.getReadOnlyMapper().getSec3CurrencyByAtm(
					a.getId()));
		});
		
		return atms;
	}
}
