
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
    @NamedQuery(name = "Wall.findByUserId", query = "SELECT w FROM Wall w WHERE w.userId = :userId")})
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
    private Image imgId;
    @JoinColumn(name = "poster_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User posterId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;

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

    public Image getImgId() {
        return imgId;
    }

    public void setImgId(Image imgId) {
        this.imgId = imgId;
    }

    public User getPosterId() {
        return posterId;
    }

    public void setPosterId(User posterId) {
        this.posterId = posterId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
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
