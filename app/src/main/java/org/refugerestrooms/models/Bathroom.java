package org.refugerestrooms.models;

import android.text.TextUtils;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.UnsupportedEncodingException;

/**
 * Placeholder
 * @author Refuge Restrooms
 */
public class Bathroom {

    //TODO Other fields
    @SerializedName("id")
    private Long mId;

    public Long getmId() {
        return mId;
    }

    public void setmId(Long mId) {
        this.mId = mId;
    }

    @SerializedName("name")
    private String mName;
    @SerializedName("street")
    private String mStreet;
    @SerializedName("city")
    private String mCity;
    @SerializedName("state")
    private String mState;
    @SerializedName("country")
    private String mCountry;
    @SerializedName("accessible")
    private boolean mAccessible;
    @SerializedName("unisex")
    private boolean mUnisex;
    @SerializedName("directions")
    private String mDirections;
    @SerializedName("comment")
    private String mComments;
    @SerializedName("downvote")
    private int mDownvote;
    @SerializedName("upvote")
    private int mUpvote;
    @SerializedName("latitude")
    private double mLatitude;
    @SerializedName("longitude")
    private double mLongitude;

    public String getNameDecoded() {
        /******************************************************************************************
        *  Following section(s) encodes result into ISO-8859-1 and then decodes it into UTF-8 using decodeString().
        *  This is necessary for displaying accented characters; previously "é" was showing as "Ã©", etc..
        *  Most likely because of an encoding redundancy from the restroom API, so this may break in the future
        *  if that is changed/fixed.
         ******************************************************************************************/
        String mNameDecoded = mName;
        if (mNameDecoded != null) {
            mNameDecoded = decodeString(mNameDecoded);
        }
        return mNameDecoded;
    }
    // Needed to create separate variable for InfoViewFragment
    public String getName() {
        return mName;
    }




    public String getmStreet() {
        return mStreet;
    }

    public void setmStreet(String mStreet) {
        this.mStreet = mStreet;
    }

    public String getmCity() {
        return mCity;
    }

    public void setmCity(String mCity) {
        this.mCity = mCity;
    }

    public String getmState() {
        return mState;
    }

    public void setmState(String mState) {
        this.mState = mState;
    }

    public String getmCountry() {
        return mCountry;
    }

    public void setmCountry(String mCountry) {
        this.mCountry = mCountry;
    }

    public boolean ismAccessible() {
        return mAccessible;
    }

    public void setmAccessible(boolean mAccessible) {
        this.mAccessible = mAccessible;
    }

    public boolean ismUnisex() {
        return mUnisex;
    }

    public void setmUnisex(boolean mUnisex) {
        this.mUnisex = mUnisex;
    }

    public String getmDirections() {
        return mDirections;
    }

    public void setmDirections(String mDirections) {
        this.mDirections = mDirections;
    }

    public String getmComments() {
        return mComments;
    }

    public void setmComments(String mComments) {
        this.mComments = mComments;
    }

    public int getmDownvote() {
        return mDownvote;
    }

    public void setmDownvote(int mDownvote) {
        this.mDownvote = mDownvote;
    }

    public int getmUpvote() {
        return mUpvote;
    }

    public void setmUpvote(int mUpvote) {
        this.mUpvote = mUpvote;
    }

    public double getmLatitude() {
        return mLatitude;
    }

    public void setmLatitude(double mLatitude) {
        this.mLatitude = mLatitude;
    }

    public double getmLongitude() {
        return mLongitude;
    }

    public void setmLongitude(double mLongitude) {
        this.mLongitude = mLongitude;
    }

    public String getAddress() {
        String address = "";
        if (!TextUtils.isEmpty(mStreet)) {
            address += mStreet + "\n";
        }
        if (!TextUtils.isEmpty(mCity)) {
            address += mCity + ", ";
        }
        if (!TextUtils.isEmpty(mState)) {
            address += mState + ", ";
        }
        if (!TextUtils.isEmpty(mCountry)) {
            address += mCountry + "\n";
        }
        try {
            address = new String(address.getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            //e.printStackTrace();
        }
        return address;
    }

    public boolean isAccessible() {
        return mAccessible;
    }

    public boolean isUnisex() {
        return mUnisex;
    }

    public String getDirections() {
        if (mDirections != null) {
            mDirections = decodeString(mDirections);
        }
        return mDirections;
    }

    public int getScore() {
        if (mUpvote == 0 && mDownvote == 0) {
            return -1;
        }
        return (mUpvote * 100) / ((mUpvote + mDownvote) * 100);
    }

    public String getComments() {
        // Same encoding fix as getDirections above
        if (mComments != null) {
            mComments = decodeString(mComments);
        }
        return mComments;
    }

    public void setName(String name) { this.mName = name; }

    @Override
    public String toString() { return mName; }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this, Bathroom.class);
    }

    public static Bathroom fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Bathroom.class);
    }

    public LatLng getLocation() {
        return new LatLng(mLatitude, mLongitude);
    }

    private String decodeString(String string) {
        try {
            string = new String(string.getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            //e.printStackTrace();
        }
        return string;
    }

}
