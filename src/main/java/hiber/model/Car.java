
package hiber.model;

        import javax.persistence.*;

@Entity
@Table(name = "user")
public class Car {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "series")
    private int series;

    @Column(name = "model")
    private String model;

    @OneToOne(mappedBy = "car")
    private User user;

    public Car() {

    }

    public Car(String model, int series) {
        this.model = model;
        this.series = series;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}