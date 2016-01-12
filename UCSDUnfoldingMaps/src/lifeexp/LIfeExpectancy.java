package lifeexp;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.marker.Marker;

/**
 * Life Expectancy example from the course.
 * 
 * @author Pawel Daniluk
 * @version 1.0
 */
public class LIfeExpectancy extends PApplet {

	private static final long serialVersionUID = 1L;

	private UnfoldingMap map;
	private Map<String, Float> lifeExpByCountry;
	private List<Feature> countries;
	private List<Marker> countryMarkers;

	public void setup() {
		size(800, 600, OPENGL);
		map = new UnfoldingMap(this, 50, 50, 700, 500, new Google.GoogleMapProvider());
		MapUtils.createDefaultEventDispatcher(this, map);

		lifeExpByCountry = loadLifeExpectancyFromCSV("LifeExpectancyWorldBank.csv");

		countries = GeoJSONReader.loadData(this, "countries.geo.json");
		countryMarkers = MapUtils.createSimpleMarkers(countries);

		map.addMarkers(countryMarkers);

	}

	public void draw() {
		map.draw();
		shadeCountries();
	}

	private Map<String, Float> loadLifeExpectancyFromCSV(String fileName) {

		Map<String, Float> lifeExpMap = new HashMap<String, Float>();

		String[] rows = loadStrings(fileName);
		for (String row : rows) {
			String[] columns = row.split(",");

			try {
				float value = Float.parseFloat(columns[5]);
				lifeExpMap.put(columns[4], value);
			} catch (Exception e) {
				System.out.println("Error:\t" + columns[4] + "\t" + columns[5]);
			}

		}

		return lifeExpMap;
	}

	private void shadeCountries() {
		for (Marker marker : countryMarkers) {
			String countryId = marker.getId();
			if (lifeExpByCountry.containsKey(countryId)) {
				float lifeExp = lifeExpByCountry.get(countryId);
				int colorLevel = (int) map(lifeExp, 40, 90, 10, 255);
				marker.setColor(color(255-colorLevel, 100, colorLevel));
			}
			else  {
				marker.setColor(color(150,150,150));
			}
		}
	}

	public void testMap() {

		Map<String, Float> map = loadLifeExpectancyFromCSV("data/LifeExpectancyWorldBank.csv");
		System.out.println(map);
	}

}
