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
@Table(name = "friends")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Friends.findAll", query = "SELECT f FROM Friends f"),
    @NamedQuery(name = "Friends.findById", query = "SELECT f FROM Friends f WHERE f.id = :id"),
    @NamedQuery(name = "Friends.findByTime", query = "SELECT f FROM Friends f WHERE f.time = :time"),
    @NamedQuery(name = "Friends.findByUser", query = "SELECT f FROM Friends f WHERE f.user = :user")})
public class Friends implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @JoinColumn(name = "friend_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User friend;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User user;

    public Friends() {
    }

    public Friends(Integer id) {
        this.id = id;
    }

    public Friends(Integer id, Date time) {
        this.id = id;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        if (!(object instanceof Friends)) {
            return false;
        }
        Friends other = (Friends) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Friends[ id=" + id + " ]";
    }
}
