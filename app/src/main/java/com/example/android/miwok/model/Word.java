package com.example.android.miwok.model;

/**
 * Created by Casa on 29/09/2016.
 */
public class Word {
    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mDrawable;
    private int mSound;
    public static final int NO_DATA = -1;

    public Word(String mDefaultTranslation, String mMiwokTranslation, int drawable) {
        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
        this.mDrawable = drawable;
        this.mSound = NO_DATA;
    }

    public Word(String mDefaultTranslation, String mMiwokTranslation, int drawable, int sound) {
        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
        this.mDrawable = drawable;
        this.mSound = sound;
    }

    public Word(String mDefaultTranslation, String mMiwokTranslation) {
        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
        this.mDrawable = NO_DATA;
        this.mSound = NO_DATA;
    }

    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getDrawable() { return mDrawable; }

    public void setDefaultTranslation(String mDefaultTranslation) {
        this.mDefaultTranslation = mDefaultTranslation;
    }

    public void setMiwokTranslation(String mMiwokTranslation) {
        this.mMiwokTranslation = mMiwokTranslation;
    }

    public void setDrawable(int mDrawable) {
        this.mDrawable = mDrawable;
    }

    public int getSound () {
        return mSound;
    }

    public boolean hasImage() {
        return mDrawable != NO_DATA;
    }

    public boolean hasSound() {
        return mSound != NO_DATA;
    }
}
