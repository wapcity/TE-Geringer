package datamodel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @since J2SE-1.8
 CREATE TABLE employee (
  id INT NOT NULL AUTO_INCREMENT,    
  name VARCHAR(30) NOT NULL,   
  age INT NOT NULL,    
  PRIMARY KEY (id));
 */
@Entity
@Table(name = "Recipe")
public class Recipe {

   @Id  // primary key
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id") // specify the column name. Without it, it will use method name
   private Integer id;

   @Column(name = "Ingrediants")
   private String Ingrediants;

   @Column(name = "Duration")
   private String Duration;
   
   @Column(name = "Directions")
   private String Directions;
   
   @Column(name = "type")
   private String type;

   public Recipe() {
   }

   public Recipe(int id, String type, String Ingrediants, String Duration, String Directions) {
      this.id = id;
      this.type = type;
      this.Ingrediants = Ingrediants;
      this.Duration = Duration;
      this.Directions = Directions;
   }

   public Recipe( String type, String Ingrediants, String Duration, String Directions) {
	
	      this.type = type;
	      this.Ingrediants = Ingrediants;
	      this.Duration = Duration;
	      this.Directions = Directions;

   }
	      
   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getIngrediants() {
      return Ingrediants;
   }

   public void setIngrediants(String Ingrediants) {
      this.Ingrediants = Ingrediants;
   }
   
   public String getDuration() {
	      return Duration;
	   }

	   public void setDuration(String Duration) {
	      this.Duration = Duration;
	   }
	   
	   
	   public String getDirections() {
		      return Directions;
		   }

		   public void setDirections(String Directions) {
		      this.Directions = Directions;
		   }
	   

   @Override
   public String toString() {
      return "Employee: " + this.id + ", " + this.type + ", " + this.Ingrediants + ", " + this.Duration + ", " + this.Directions;
   }
}