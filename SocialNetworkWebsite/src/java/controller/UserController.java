package controller;

import java.io.ByteArrayInputStream;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import model.Friends;
import model.Image;
import model.Requests;
import model.User;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import server.EMF;

/**
 *
 * @author Xi
 */
@ManagedBean
@SessionScoped
public class UserController {

    private User user;
    private String screenName;
    private String passWd;
    private EntityManager em;
    private boolean isLoggedIn;
    private String oldPassword;
    private String newPassword;
    private String confPassword;
    private UIComponent component;
    private List<String> interestOptions;
    private StreamedContent profileImage;
    private User friend;
    private User target;

    /**
     * Creates a new instance of UserController
     */
    public UserController() {
        em = EMF.createEntityManager();
    }

    public String login() {
        List<User> users = (List<User>) em.createNamedQuery("User.findByScreenName").setParameter("screenName", screenName).getResultList();
        if (users.isEmpty()) {
            FacesMessage message = new FacesMessage("User name does not exist!");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else if ((user = users.get(0)).getConfirmationStatus() == 0) {
            FacesMessage message = new FacesMessage("This account has not been activitied yet.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else if (!(user = users.get(0)).getPasswd().equals(passWd) || !(user = users.get(0)).getScreenName().equals(screenName)) {
            FacesMessage message = new FacesMessage("User name and password dose not match!");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            isLoggedIn = true;
            FacesMessage message = new FacesMessage("Login succesfully!");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "/user/home.xhtml?faces-redirect=true";
        }
        return null;
    }

    public String logout() {
        isLoggedIn = false;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }

    public void changePassword() {
        if (!user.getPasswd().equals(oldPassword)) {
            FacesMessage message = new FacesMessage("The old password is not correct.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(user);
            user.setPasswd(newPassword);
            tx.commit();
            FacesMessage message = new FacesMessage("Password changed successfully.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void changeRealName() {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(user);
        tx.commit();
        FacesMessage message = new FacesMessage("Real name changed successfully.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void changeGender() {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(user);
        tx.commit();
        FacesMessage message = new FacesMessage("Gender changed successfully.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void changeInterests() {
        String interests = "";
        for (String s : interestOptions) {
            interests += s + " ";
        }
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(user);
        user.setInterests(interests);
        tx.commit();
        System.out.println(user.getInterests());
        FacesMessage message = new FacesMessage("Interests changed successfully.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void changeBirthday() {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(user);
        tx.commit();
        FacesMessage message = new FacesMessage("Date of birth changed successfully.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void addProfile(FileUploadEvent event) {
        byte[] imgBytes = event.getFile().getContents();
        Image img = new Image();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(img);
        img.setImg(imgBytes);
        tx.commit();
        tx = em.getTransaction();
        tx.begin();
        em.persist(user);
        user.setProfileImg(img);
        tx.commit();
        FacesMessage msg = new FacesMessage("Your profile image is updated!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void addFriend() {
        if (friend.equals(user)) {
            FacesMessage msg = new FacesMessage("You cannot add yourself as a friend.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        } else {
            List<Friends> list2 = (List<Friends>) em.createNamedQuery("Friends.findByUser").setParameter("user", user).getResultList();
            if (!list2.isEmpty()) {
                for (Friends f : list2) {
                    if (f.getFriend().equals(friend)) {
                        FacesMessage msg = new FacesMessage("You already have been friends.");
                        FacesContext.getCurrentInstance().addMessage(null, msg);
                        return;
                    }
                }
            }
        }
        
        List<Requests> list = (List<Requests>) em.createNamedQuery("Requests.findByRequestee").setParameter("requestee", friend).getResultList();
        if (list.isEmpty()) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            Requests r = new Requests();
            em.persist(r);
            r.setRequester(user);
            r.setRequestee(friend);
            tx.commit();
            FacesMessage msg = new FacesMessage("Your request has been sent.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage msg = new FacesMessage("You already sent a request to this user.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }


    public void agree() {
        List<Requests> list = (List<Requests>) em.createNamedQuery("Requests.findByRequestee").setParameter("requestee", user).getResultList();
        Requests request = null;
//        System.out.println(user.getScreenName() + " " + target.getScreenName());
        for (Requests r : list) {
            if ((r.getRequester().equals(target)) && (r.getRequestee().equals(user))) {
                request = r;
            }
        }
//        System.out.println(request);

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Friends f1 = new Friends();
        Friends f2 = new Friends();
        em.persist(f1);
        em.persist(f2);
        f1.setUser(user);
        f1.setFriend(target);
        f2.setUser(target);
        f2.setFriend(user);
        em.remove(request);
        tx.commit();
        FacesMessage message = new FacesMessage(target.getScreenName() + " is your friend now.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void decline() {
        List<Requests> list = (List<Requests>) em.createNamedQuery("Requests.findByRequestee").setParameter("requestee", user).getResultList();
        Requests request = null;
        for (Requests r : list) {
            if ((r.getRequester() == target) && (r.getRequestee() == user)) {
                request = r;
            }
        }
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(request);
        tx.commit();
        FacesMessage message = new FacesMessage("You've declined " + target.getScreenName() + "'s request.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void deleteFriend() {
        List<Friends> list1 = (List<Friends>) em.createNamedQuery("Friends.findByUser").setParameter("user", user).getResultList();
        List<Friends> list2 = (List<Friends>) em.createNamedQuery("Friends.findByUser").setParameter("user", target).getResultList();
        Friends friendship1 = null;
        Friends friendship2 = null;
        for (Friends f : list1) {
            if ((f.getFriend().equals(target)) && (f.getUser().equals(user))) {
                friendship1 = f;
            }
        }

        for (Friends f : list2) {
            if ((f.getFriend().equals(user)) && (f.getUser().equals(target))) {
                friendship2 = f;
            }
        }
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(friendship1);
        tx.commit();
        tx = em.getTransaction();
        tx.begin();
        em.remove(friendship2);
        tx.commit();
        FacesMessage message = new FacesMessage(target.getScreenName() + " has been removed from your friend list.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public String viewProfile() {
        return "dialog:otherProfile";
    }

    public void clearProfile() {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(user);
        user.setProfileImg(null);
        tx.commit();
        FacesMessage msg = new FacesMessage("Your profile image is deleted!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public Boolean hasProfile() {
        if (user.getProfileImg() != null) {
            return true;
        } else {
            return false;
        }
    }
    
    public String requestCode() {
        return "/user/code.xhtml?faces-redirect=true";
    }

    public User getUser() {
        return user;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getPassWd() {
        return passWd;
    }

    public void setPassWd(String passWd) {
        this.passWd = passWd;
    }

    public boolean isIsLoggedIn() {
        return isLoggedIn;
    }

    public String getConfPassword() {
        return confPassword;
    }

    public void setConfPassword(String confPassword) {
        this.confPassword = confPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public UIComponent getComponent() {
        return component;
    }

    public void setComponent(UIComponent component) {
        this.component = component;
    }

    public List<String> getInterestOptions() {
        return interestOptions;
    }

    public void setInterestOptions(List<String> interestOptions) {
        this.interestOptions = interestOptions;
    }

    public StreamedContent getProfileImage() {
        StreamedContent imgStream = null;
        Image img = user.getProfileImg();
        if (img != null) {
            byte[] imgBytes = user.getProfileImg().getImg();
            if (imgBytes != null) {
                if (imgBytes.length > 0) {
                    imgStream = new DefaultStreamedContent(new ByteArrayInputStream(imgBytes), "image/png");
                }
            }
        }
        return imgStream;

    }

    public void setProfileImage(StreamedContent profileImage) {
        this.profileImage = profileImage;
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }

    public User getTarget() {
        return target;
    }

    public void setTarget(User target) {
        this.target = target;
    }
}