package databaas.dataplace.database.impl;

import databaas.dataplace.database.DatabaseConnectInfo;

public class DatabaseConnectPlace implements DatabaseConnectInfo {

	private final String ip;
	private final int port;
	private final String username;
	private final String password;

	public DatabaseConnectPlace(String place) {
		this (place, 999);
	}

	public DatabaseConnectPlace(String ip, int port) {
		this(ip, port, null, null);
	}

	public DatabaseConnectPlace(String ip, int port, String username, String password) {
		this.ip = ip;
		this.port = port;
		this.username = username;
		this.password = password;
	}

	public String getIp() {
		return ip;
	}

	public String getUrl() {
		return ip;
	}

	public int getPort() {
		return port;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}

}
