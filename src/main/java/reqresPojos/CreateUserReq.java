package reqresPojos;

public class CreateUserReq {
    public CreateUserReq() {
    }

    public CreateUserReq(String name, String job) {
        this.name = name;
        this.job = job;
    }

    private String name;
    private String job;


    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setJob(String job) {
        this.job = job;
    }


}
