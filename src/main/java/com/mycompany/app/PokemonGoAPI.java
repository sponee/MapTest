package com.mycompany.app;

// acts as a wrapper for the initial classes we need from the Pokemon Go API

import okhttp3.OkHttpClient;

import com.pokegoapi.api.PokemonGo;
import com.pokegoapi.auth.GoogleAutoCredentialProvider;
import com.pokegoapi.util.hash.HashProvider;
import com.pokegoapi.util.hash.pokehash.PokeHashKey;
import com.pokegoapi.util.hash.pokehash.PokeHashProvider;

public class PokemonGoAPI {
	private PokemonGo goClient;
	private final String POKEHASH_KEY = "";
	protected OkHttpClient httpClient;
	protected double lat;
	protected double lng;
	protected String username;
	protected String password;
	
	public PokemonGoAPI(double initLat, double initLng, String initUsername, String initPassword) throws Exception {
		lat = initLat;
		lng = initLng;
		username = initUsername;
		password = initPassword;
		httpClient = new OkHttpClient();
	    goClient = new PokemonGo(httpClient);
	}
	
	public PokemonGo getGoClient() {
		return goClient;
	}
	
	public void login() throws Exception {
		HashProvider hasher = getHashProvider();
		GoogleAutoCredentialProvider provider = new GoogleAutoCredentialProvider(httpClient, "kyle.sponheim@gmail.com", "James1848180t");
		getGoClient().login(provider, hasher);
		getGoClient().setLocation(lat, lng, 0);
	    
	}
	
	private HashProvider getHashProvider() {
		return new PokeHashProvider(PokeHashKey.from(POKEHASH_KEY), true);
	}

}
