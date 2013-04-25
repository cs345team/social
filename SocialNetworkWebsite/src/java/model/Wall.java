
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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Xi
 */
@Entity
@Table(name = "wall")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Wall.findAll", query = "SELECT w FROM Wall w"),
    @NamedQuery(name = "Wall.findById", query = "SELECT w FROM Wall w WHERE w.id = :id"),
    @NamedQuery(name = "Wall.findByTime", query = "SELECT w FROM Wall w WHERE w.time = :time"),
    @NamedQuery(name = "Wall.findByUser", query = "SELECT w FROM Wall w WHERE w.user = :user")})
public class Wall implements Serializable {
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
    @Lob
    @Column(name = "text")
    private String text;
    @JoinColumn(name = "img_id", referencedColumnName = "id")
    @ManyToOne
    private Image image;
    @JoinColumn(name = "poster_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User poster;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User user;

    public Wall() {
    }

    public Wall(Integer id) {
        this.id = id;
    }

    public Wall(Integer id, Date time) {
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }



    public User getPoster() {
        return poster;
    }

    public void setPoster(User poster) {
        this.poster = poster;
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
        if (!(object instanceof Wall)) {
            return false;
        }
        Wall other = (Wall) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Wall[ id=" + id + " ]";
    }
    
}
