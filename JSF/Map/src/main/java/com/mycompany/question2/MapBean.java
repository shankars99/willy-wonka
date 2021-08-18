import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class MapBean {
 
    private String location = "";
    private String map = "";
    private String key = "AIzaSyCaI5_VTtBgnD-ueNJMsHQHKW2xFazQRYk";
    public MapBean() {
        System.out.println("Created GreetingsBean instance...");
    }
    
    public String getMap(){
        String map = "https://www.google.com/maps/embed/v1/search?q="+location+"&key="+key;
        return map;    
    }
    
    public String getLocation() {
        return location;
    }
     
    public void setLocation(String location) {
        this.location = location.trim();
    }
     
    public String showMap() {
        return "location";
    }
}