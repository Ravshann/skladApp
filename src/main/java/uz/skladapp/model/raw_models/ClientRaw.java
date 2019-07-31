package uz.skladapp.model.raw_models;

public class ClientRaw {
    private Long client_ID;
    private String client_name;
    private String region;

    public ClientRaw() {
    }

    public ClientRaw(Long client_ID, String client_name, String region) {
        this.client_ID = client_ID;
        this.client_name = client_name;
        this.region = region;
    }

    public Long getClient_ID() {
        return client_ID;
    }

    public void setClient_ID(Long client_ID) {
        this.client_ID = client_ID;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
