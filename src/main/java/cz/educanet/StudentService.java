package cz.educanet;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.IOException;
import java.util.List;

@Named
@ApplicationScoped
public class StudentService {
    @Inject
    private StudentBean sb;

    public void add(String fullname, String dateOfBirth, float averageGrade) {
        sb.add(fullname, dateOfBirth, averageGrade);
    }

    public List<StudentEntity> getAll() {
        return sb.getAll();
    }

    public StudentEntity getStudent() {
        String param = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        return sb.getStudent(Integer.parseInt(param));
    }

    public Double getAverage() {
        return sb.getAverage();
    }

    public void update(String fullname, String dateOfBirth, float averageGrade) throws IOException {
        System.out.println("FN " + fullname);
        System.out.println("DOB " + dateOfBirth);
        System.out.println("AG " + averageGrade);
        String param = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        sb.updateStudent(fullname, dateOfBirth, averageGrade, Integer.parseInt(param));
        FacesContext.getCurrentInstance().getExternalContext().redirect("/Test050523/index.xhtml");
    }

    public void delete(int id) {
        sb.delete(id);
    }
}
