package test.entity;

public class CEO {
    private Car car;

    @Override
    public String toString() {
        return "CEO{" +
                "car=" + car +
                '}';
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
