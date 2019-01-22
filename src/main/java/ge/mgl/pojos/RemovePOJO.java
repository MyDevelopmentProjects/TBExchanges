package ge.mgl.pojos;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class RemovePOJO {

    @NotNull
    @NotEmpty
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
