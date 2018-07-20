package model.project;

import model.user.User;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Classe que representa un projecte amb tots els seus elements
 */
public class Project implements Serializable{

    private final static int INVALID_INDEX = -1;
    public final static int serialVersionUID = 1235;

    private String id;
    private String name;
    private Color color;
    private ArrayList<Category> categories;
    private ArrayList<User> users;
    private byte[] background;
    private boolean isOwner;
    private String ownerName;

    /**
     * Constructor sense parametres
     */
    public Project() {
        categories = new ArrayList<>();
        users = new ArrayList<>();
    }

    /**
     * Constructor amb parametres
     * @param id Id del projecte
     * @param name Nom del projecte
     * @param color Color del projecte
     * @param isOwner Boolea que indica si l'usuari es propietari o no
     */
    public Project(String id, String name, Color color, boolean isOwner) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.isOwner = isOwner;
        categories = new ArrayList<>();
        users = new ArrayList<>();
    }

    /**
     * Constructor amb mes parametres
     * @param id Id del projecte
     * @param name Nom del projecte
     * @param color Color del projecte
     * @param categories Columnes del projecte
     * @param users Usuaris del projecte
     * @param isOwner Indica si l'usuari es owner
     */
    public Project(String id, String name, Color color, ArrayList<Category> categories, ArrayList<User> users, boolean isOwner) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.categories = categories;
        this.users = users;
        this.isOwner = isOwner;
    }

    /**
     * Getter de l'id
     * @return Id
     */
    public String getId() {
        return id;
    }

    /**
     * Setter de l'id
     * @param id Id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter del propietari del projecte
     * @return Nom del propietari
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * Setter del nom del propietari del projecte
     * @param ownerName Nom del propietari
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    /**
     * Getter del nom
     * @return Nom
     */
    public String getName() {
        return name;
    }

    /**
     * Setter del name
     * @param name Nom
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter del color
     * @return Color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Setter del color
     * @param color Color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Retorna la mida de les categories
     * @return Mida de les categories
     */
    public int getCategoriesSize() {
        return categories.size();
    }

    /**
     * Getter de les categories
     * @return Categories
     */
    public ArrayList<Category> getCategories() {
        return categories;
    }

    /**
     * Getter de l'index d'una categoria
     * @param category Categoria de la que es vol treure l'index
     * @return Index de la categoria
     */
    public int getCategoryIndex(Category category) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId().equals(category.getId())) {
                return i;
            }
        }
        return INVALID_INDEX;
    }

    /**
     * Retorna una categoria a partir d'un index
     * @param categoryIndex Index de la categoria que volem obtenir
     * @return Categoria
     */
    public Category getCategory(int categoryIndex) {
        if(categoryIndex < categories.size()) {
            return categories.get(categoryIndex);
        } else {
            return null;
        }
    }

    /**
     * Retorna una categoria a partir d'una id
     * @param categoryId Id de la categoria que volem obtenir
     * @return Categoria
     */
    public Category getCategoryWithId(String categoryId) {

        for(Category category : categories) {
            if(category.getId().equals(categoryId)) {
                return category;
            }
        }
        return null;
    }

    /**
     * Setter d'una categoria que, en cas que ja existeixi, la sobreescriura i en cas que no existeixi, l'afegira
     * @param category Categoria que volem afegir
     */
    public void addCategory(Category category) {
        if(category != null) {

            //Find category
            for (int i = 0; i < categories.size(); i++) {
                if(category.getId().equals(categories.get(i).getId())) {
                    categories.get(i).setName(category.getName());
                    categories.get(i).setOrder(category.getOrder());
                    return;
                }
            }

            //Category not found
            category.setOrder(categories.size());
            categories.add(category);

        }
    }

    /**
     * Esborra una categoria
     * @param category Categoria que volem esborrar
     */
    public void deleteCategory(Category category) {
        if(categories.contains(category)) {
            categories.remove(category);
        }
    }

    /**
     * Swap de dues columnes colindants
     * @param firstCategoryIndex Categoria que volem intercanviar
     * @param secondCategoryIndex Categoria que volem intercanviar
     */
    public void swapCategories(int firstCategoryIndex, int secondCategoryIndex) {
        if(firstCategoryIndex >= 0 && firstCategoryIndex < categories.size() && secondCategoryIndex < categories.size()
                && secondCategoryIndex >= 0) {
            Category category1 = categories.get(firstCategoryIndex);
            Category category2 = categories.get(secondCategoryIndex);
            int aux = category1.getOrder();
            category1.setOrder(category2.getOrder());
            category2.setOrder(aux);
            categories.set(firstCategoryIndex, category2);
            categories.set(secondCategoryIndex, category1);
        }
    }

    /**
     * Getter de la mida dels usuaris
     * @return Mida dels usuaris
     */
    public int getUsersSize() {
        return users.size();
    }

    /**
     * Getter dels usuaris
     * @return Usuaris
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Getter dels usuaris a excepcio de l'usuari per parametre
     * @param user Usuari a excloure
     * @return Usuaris (a excecpio del parametre)
     */
    public ArrayList<User> getOtherUsers(User user) {
        ArrayList<User> otherUsers = new ArrayList<>();
        otherUsers.addAll(users);
        otherUsers.remove(user);
        return otherUsers;
    }

    /**
     * Setter dels usuari
     * @param users Usuaris a settejar
     */
    public void setUsers(ArrayList<User> users) {
        if(users != null) {
            this.users = users;
        }
    }

    /**
     * Getter d'un index a partir d'un usuari
     * @param user Usuari a partir del qual volem rebre l'index
     * @return Index de l'suari
     */
    public int getUserIndex(User user) {
        if(users.contains(user)) {
            return users.indexOf(user);
        } else {
            return INVALID_INDEX;
        }
    }

    /**
     * Getter d'un usuari a partir d'un index
     * @param userIndex Index a partir del qual volem aconseguir l'usuari
     * @return Usuari
     */
    public User getUser(int userIndex) {
        if(userIndex >= 0 && userIndex < users.size()) {
            return users.get(userIndex);
        } else {
            return null;
        }
    }

    /**
     * Afegeix un usuari
     * @param user Usuari a afegir
     */
    public void addUser(User user) {
        if(!users.contains(user)) {
            users.add(user);
        }
    }

    /**
     * Esborra un usuari a partir d'un index
     * @param userIndex Index de l'usuari que volem esborrar
     */
    public void deleteUser(int userIndex) {
        if(userIndex >= 0 && userIndex < users.size()) {
            users.remove(userIndex);
        }
    }

    /**
     * Getter del background
     * @return Background obtingut, si no n'hi ha retorna null
     */
    public BufferedImage getBackground() {
        try {
            if (background != null) {
                return ImageIO.read(new ByteArrayInputStream(this.background));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Setter del background
     * @param background Background
     */
    public void setBackground(BufferedImage background) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(background, "png", baos);
            baos.flush();
            this.background = baos.toByteArray();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter del camp isOwner
     * @return Si es propietari
     */
    public boolean isOwner() {
        return isOwner;
    }

    /**
     * Setter del camp isOwner
     * @param isOwner Si es propietari
     */
    public void setOwner(boolean isOwner) {
        this.isOwner = isOwner;
    }

    /**
     * Equals
     * @param o Objecte
     * @return Si equival
     */
    @Override
    public boolean equals(Object o) {

        if(this == o) {
            return true;
        }

        if(o == null || getClass() != o.getClass()) {
            return false;
        }

        Project project = (Project) o;
        return Objects.equals(id, project.id);

    }

    /**
     * Hashcode
     * @return Hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, color, categories, users, background);
    }

}
