package com.lusostudios.asphaltcalc.calculations;

public class Calculations_old {

	public double volume;

	// Calculations_old based on US standard units
	public double SlabRectangle(String units, double length, double width,
			double thickness, double waste) {

		if ("US".equals(units)) {
			volume = ((length * width * (thickness / 12)) / 27)
					* ((waste / 100) + 1);

			// bag1Constant = .45 / 27; // One sixty pound bag will yield .45
			// cubic
			// feet. Divided by 27 to get CY.
			// bag2Constant = .60 / 27; // One Eighty pound bag will yield .67
			// cubic feet. Divided by 27 to get CY.

		} else if ("Metric".equals(units)) {
			volume = (length * width * (thickness / 100)) * ((waste / 100) + 1);
			// bag1Constant = .0125;
			// bag2Constant = volume;
		}

		/*
		 * volumeWaste = volume * waste; bags1 = volume / bag1Constant; bags2 =
		 * volume / bag2Constant; // Puts all answers into a double array
		 * answers = new double[4]; answers[0] = volume; answers[1] =
		 * volumeWaste; answers[2] = bags1; answers[3] = bags2;
		 */
		return volume;
	}

	public double SlabCircle(String units, double radius, double thickness,
			double waste) {

		volume = (Math.PI * Math.pow(radius, 2) * (thickness / 12)) / 27;

		if ("US".equals(units)) {
			volume = ((Math.PI * Math.pow(radius, 2) * (thickness / 12)) / 27)
					* ((waste / 100) + 1);
		} else if ("Metric".equals(units)) {
			volume = (Math.PI * Math.pow(radius, 2) * (thickness / 100))
					* ((waste / 100) + 1);
		}

		return volume;
	}

	public double SlabArea(String units, double area, double thickness,
			double waste) {

		if ("US".equals(units)) {
			volume = ((area * (thickness / 12)) / 27) * ((waste / 100) + 1);
		} else if ("Metric".equals(units)) {
			volume = (area * (thickness / 100)) * ((waste / 100) + 1);
		}

		return volume;
	}

	public double Curb(String units, double height, double thickness,
			double length, double waste) {

		if ("US".equals(units)) {
			volume = (((height / 12) * (thickness / 12) * length) / 27) * ((waste / 100) + 1);
		} else if ("Metric".equals(units)) {
			volume = ((height / 100) * (thickness / 100) * length) * ((waste / 100) + 1);
		}

		return volume;
	}

	public double Curb_and_Gutter(String units, double height,
			double thickness, double gutterHeight, double gutterWidth,
			double length, double waste) {

		if ("US".equals(units)) {
			volume = ((((height / 12) * (thickness / 12) + (gutterHeight / 12)
					* (gutterWidth / 12)) * length) / 27) * ((waste / 100) + 1);
		} else if ("Metric".equals(units)) {
			volume = (((height / 100) * (thickness / 100) + (gutterHeight / 100)
					* (gutterWidth / 100))
					* length) * ((waste / 100) + 1);
		}

		return volume;
	}

	public double Wall(String units, double height, double thickness,
			double length, double waste) {
		if ("US".equals(units)) {
			volume = ((length * height * (thickness / 12)) / 27) * ((waste / 100) + 1);
		} else if ("Metric".equals(units)) {
			volume = (length * height * (thickness / 100)) * ((waste / 100) + 1);
		}

		return volume;
	}

	public double Footing(String units, double height, double width,
			double length, double waste) {
		if ("US".equals(units)) {
			volume = (((height / 12) * (width / 12) * length) / 27) * ((waste / 100) + 1);
		} else if ("Metric".equals(units)) {
			volume = ((height / 12) * (width / 12) * length) * ((waste / 100) + 1);

		}

		return volume;
	}

	public double Column_Round(String units, double radius, double height,
			double waste) {

		if ("US".equals(units)) {
			volume = ((Math.PI * Math.pow((radius / 12), 2) * height) / 27) * ((waste / 100) + 1);
		} else if ("Metric".equals(units)) {
			volume = (Math.PI * Math.pow((radius / 100), 2) * height) * ((waste / 100) + 1);
		}
		return volume;
	}

	public double Column_Square(String units, double length, double width,
			double height, double waste) {
		if ("US".equals(units)) {
			volume = (((length / 12) * (width / 12) * height) / 27) * ((waste / 100) + 1);
		} else if ("Metric".equals(units)) {
			volume = ((length / 100) * (width / 100) * height) * ((waste / 100) + 1);
		}
		return volume;
	}
}
