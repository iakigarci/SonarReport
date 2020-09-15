package models;

public class Language {

    /**
     * Language key
     */
    private String key;
    /**
     * Language name
     */
    private String name;

    public Language(String key, String name) {
        super();
        this.key = key;
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "key: " + key + " " + "name: " + name;
    }

}
