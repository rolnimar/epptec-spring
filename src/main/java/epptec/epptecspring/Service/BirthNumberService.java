package epptec.epptecspring.Service;


import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class BirthNumberService {

	public Integer calculateAgeFromBirthNumber(String birthNumber){
		LocalDate birthDate = getDateFromBirthNumber(birthNumber);
		LocalDate currentDate = LocalDate.now();
		return Period.between(birthDate, currentDate).getYears();
	}

	private LocalDate getDateFromBirthNumber(String birthNumber){
		LocalDate date = LocalDate.now();
		int year = Integer.parseInt(birthNumber.substring(0,2));
		int month = Integer.parseInt(birthNumber.substring(2,4));
		int day = Integer.parseInt(birthNumber.substring(4,6));

		if(date.getYear()-year < 2000){
			year +=1900;
		} else {
			year+=2000;
		}

		if(month>12){
			month-=50;
		}

		return LocalDate.of(year,month,day);
	}
}
