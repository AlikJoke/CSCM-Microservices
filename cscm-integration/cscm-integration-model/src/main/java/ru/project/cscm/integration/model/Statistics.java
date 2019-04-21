package ru.project.cscm.integration.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import ru.project.cscm.integration.model.enums.StatisticsType;

@Entity
@Table(name = "statistics")
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Lob
    @Column(name = "stats", nullable = false, updatable = false)
    private byte[] stats;

    @Column(name = "type", length = 256)
    @Enumerated(EnumType.STRING)
    private StatisticsType type;

    protected Statistics() {
        super();
    }

    public Statistics(final StatisticsType type, final byte[] stats) {
        this(null, type, stats);
    }

    public Statistics(final Integer id, final StatisticsType type, final byte[] stats) {
        this.id = id;
        this.stats = stats;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public byte[] getStats() {
        return stats;
    }

    public StatisticsType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Statistics [id=" + id + ", type=" + type + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + Arrays.hashCode(stats);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Statistics other = (Statistics) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (!Arrays.equals(stats, other.stats))
            return false;
        return true;
    }
}
