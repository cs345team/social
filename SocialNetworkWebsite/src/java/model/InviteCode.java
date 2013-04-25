package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mcsadmin
 */
@Entity
@Table(name = "invite_code")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InviteCode.findAll", query = "SELECT i FROM InviteCode i"),
    @NamedQuery(name = "InviteCode.findById", query = "SELECT i FROM InviteCode i WHERE i.id = :id"),
    @NamedQuery(name = "InviteCode.findByCode", query = "SELECT i FROM InviteCode i WHERE i.code = :code"),
    @NamedQuery(name = "InviteCode.findByTime", query = "SELECT i FROM InviteCode i WHERE i.time = :time"),
    @NamedQuery(name = "InviteCode.findByOwner", query = "SELECT i FROM InviteCode i WHERE i.owner = :owner")})
public class InviteCode implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User owner;

    public InviteCode() {
    }

    public InviteCode(Integer id) {
        this.id = id;
    }
    
    public InviteCode(Integer id, String code, Date time) {
        this.id = id;
        this.code = code;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InviteCode)) {
            return false;
        }
        InviteCode other = (InviteCode) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.InviteCode[ id=" + id + " ]";
    }
}
