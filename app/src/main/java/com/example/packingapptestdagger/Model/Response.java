package com.example.packingapptestdagger.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "user")

public class Response implements Serializable {
	@PrimaryKey(autoGenerate = true)
	private int uid;



	@ColumnInfo(name = "difficulty")
	@SerializedName("difficulty")
	private int difficulty;
	@ColumnInfo(name = "image")
	@SerializedName("image")
	private String image;
	@ColumnInfo(name = "fats")
	@SerializedName("fats")
	private String fats;
	@ColumnInfo(name = "thumb")
	@SerializedName("thumb")
	private String thumb;
	@ColumnInfo(name = "proteins")
	@SerializedName("proteins")
	private String proteins;
	@ColumnInfo(name = "name")
	@SerializedName("name")
	private String name;
	@ColumnInfo(name = "carbos")
	@SerializedName("carbos")
	private String carbos;
	@ColumnInfo(name = "description")
	@SerializedName("description")
	private String description;
	@ColumnInfo(name = "calories")
	@SerializedName("calories")
	private String calories;
	@ColumnInfo(name = "id")
	@SerializedName("id")
	private String id;
	@ColumnInfo(name = "time")
	@SerializedName("time")
	private String time;
	@ColumnInfo(name = "headline")
	@SerializedName("headline")
	private String headline;

	public void setDifficulty(int difficulty){
		this.difficulty = difficulty;
	}

	public int getDifficulty(){
		return difficulty;
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setFats(String fats){
		this.fats = fats;
	}

	public String getFats(){
		return fats;
	}

	public void setThumb(String thumb){
		this.thumb = thumb;
	}

	public String getThumb(){
		return thumb;
	}

	public void setProteins(String proteins){
		this.proteins = proteins;
	}

	public String getProteins(){
		return proteins;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setCarbos(String carbos){
		this.carbos = carbos;
	}

	public String getCarbos(){
		return carbos;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setCalories(String calories){
		this.calories = calories;
	}

	public String getCalories(){
		return calories;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setTime(String time){
		this.time = time;
	}

	public String getTime(){
		return time;
	}

	public void setHeadline(String headline){
		this.headline = headline;
	}

	public String getHeadline(){
		return headline;
	}
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}
}