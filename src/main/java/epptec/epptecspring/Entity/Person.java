package epptec.epptecspring.Entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Person {
	private String name;
	private String surname;
	private String birthNumber;
	private Integer age;

	@Override
	public String toString() {
		return "Birth number: ".concat(birthNumber)
						.concat(", Surname: ")
						.concat(surname)
						.concat(", Name: ")
						.concat(name)
						.concat(", Age: ")
						.concat(age.toString());
	}

	public String getBirthNumber() {
		return birthNumber;
	}

	public void setAge(Integer age) {
		this.age = age;
	}


}
