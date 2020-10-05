package com.example.packingapptestdagger.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserResponse {

	@SerializedName("records")
	private ArrayList<User> records;

	public void setRecords(ArrayList<User> records) {
		this.records = records;
	}

	public ArrayList<User> getRecords() {
		return records;
	}
}