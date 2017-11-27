package com.mycompany.app;

import java.util.Set;
import java.util.Arrays;
import java.util.Iterator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReference;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;

import com.pokegoapi.api.map.pokemon.CatchablePokemon;

public class MapTest extends Application {
  private MapView mapView;
  private final static double LAT = 0.0;
  private final static double LNG = 0.0;
  private final String USERNAME = "";
  private final String PASSWORD = "";
  
  @Override
  public void start(Stage stage) throws Exception {
	// login to pokemon go and get a new instance of the API
	  
	PokemonGoAPI go = new PokemonGoAPI(LAT, LNG, USERNAME, PASSWORD);
	go.login();
	  
    // create stack pane and application scene
    StackPane stackPane = new StackPane();
    Scene scene = new Scene(stackPane);
    
    // set title, size, and add scene to stage
    stage.setTitle("Pokémon Go!");
    stage.setWidth(800);
    stage.setHeight(700);
    stage.setScene(scene);
    stage.show();
    
    Set<CatchablePokemon> catchablePokemon = go.getGoClient().getMap().getMapObjects().getPokemon(); // create a Set of catchable pokemon
    Iterator<CatchablePokemon> pokemonIterator = catchablePokemon.iterator();   	   // turn the set of catchable pokemon into an iterator
    GraphicsOverlay graphicsOverlay = new GraphicsOverlay();    					   // create a graphics overlay
    ArcGISMap map = new ArcGISMap(Basemap.createImagery());						  	   // add graphics overlay to the map view
    SimpleMarkerSymbol redCircleSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, 0xFFFF0000, 10); // create a red (0xFFFF0000) circle simple marker symbol
    SpatialReference SPATIAL_REFERENCE = SpatialReferences.getWgs84();				   // create a new spatial reference
    Graphic[] graphics = new Graphic[catchablePokemon.size()]; 						   // create a new Graphics array with catchablePokemon.size() elements
   
    // set the map to be displayed in this view
    mapView = new MapView();
    mapView.setMap(map);
    mapView.getGraphicsOverlays().add(graphicsOverlay);
    
    
    // programmatically add Graphics to the graphics array 
    int index = 0;
    while(pokemonIterator.hasNext()) {
    	 CatchablePokemon pokemon = pokemonIterator.next();
    	 graphics[index] = new Graphic(new Point(pokemon.getLongitude(), pokemon.getLatitude(), SPATIAL_REFERENCE), redCircleSymbol);
    	index++;
    }
    
    graphicsOverlay.getGraphics().addAll(Arrays.asList(graphics));
    
    // add the map view to stack pane
    stackPane.getChildren().add(mapView);
  }
  
  /**
   * Stops and releases all resources used in application.
   */
  
  @Override
  public void stop() throws Exception {

    if (mapView != null) {
      mapView.dispose();
    }
  }
  
  public static void main(String[] args) {    
    Application.launch(args);
  }
  
}
