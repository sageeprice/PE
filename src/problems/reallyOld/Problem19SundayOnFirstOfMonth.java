package problems.reallyOld;

public class Problem19SundayOnFirstOfMonth {

	public static void main(String[] args) {
		int goodSundays = 0;
		int year = 0;
		int month = 1;
		int day = 1;
		while (year < 101) {
			switch (month) {
				case 1: day+=3;
				case 2:
					if (year % 4 == 0 && year != 100) {
						day+=1;
					} else {
						;
					}
				case 3: day+=3;
				case 4: day+=2;
				case 5: day+=3;
				case 6: day+=2;
				case 7: day+=3;
				case 8: day+=3;
				case 9: day+=2;
				case 10: day+=3;
				case 11: day+=2;
				case 12: day+=3;
			}
			day %= 7;
			if (day == 0)
				goodSundays++;
			month++;
			if (month == 13) {
				month = 1;
				year++;
			}	
		}
		System.out.println("Number of Sundays is " + goodSundays);

	}

}
