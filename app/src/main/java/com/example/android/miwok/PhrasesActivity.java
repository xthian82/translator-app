package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {
    private static final String TAG = "[PhrasesActivity]";
    private MediaPlayer mMediaPlayer;

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    private AudioManager am;

    private AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                // Audio Focus was temporarily stolen, but will be back soon. (i.e. for a phone call)
                mMediaPlayer.pause();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // lost the Audio Focus. (i.e. the user started some other playback app)
                releaseMediaPlayer();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // Lower the volume, because something else is also playing audio over you. (for notifications or navigation directions)
                mMediaPlayer.pause();
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // hold the Audio Focus again! (i.e. the phone call ended or the nav directions)
                mMediaPlayer.start();
            }
        }
    };


    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            if (am != null) am.abandonAudioFocus(afChangeListener);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("Where are you going?", "minto wuksus", Word.NO_DATA, R.raw.phrase_where_are_you_going));
        words.add(new Word("What is your name?", "tinnә oyaase'nә", Word.NO_DATA, R.raw.phrase_what_is_your_name));
        words.add(new Word("My name is...", "oyaaset...", Word.NO_DATA, R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?", "michәksәs?", Word.NO_DATA, R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I’m feeling good", "kuchi achit", Word.NO_DATA, R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming?", "әәnәs'aa?", Word.NO_DATA, R.raw.phrase_are_you_coming));
        words.add(new Word("Yes, I’m coming", "hәә’ әәnәm", Word.NO_DATA, R.raw.phrase_yes_im_coming));
        words.add(new Word("I’m coming", "әәnәm", Word.NO_DATA, R.raw.phrase_im_coming));
        words.add(new Word("Let’s go", "yoowutis", Word.NO_DATA, R.raw.phrase_lets_go));
        words.add(new Word("Come here", "әnni'nem", Word.NO_DATA, R.raw.phrase_come_here));

        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_phrases);

        ListView listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word item = words.get(position);

                if (item.hasSound()) {
                    releaseMediaPlayer();

                    // Request audio focus for playback
                    int result = am.requestAudioFocus(afChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                    if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                        mMediaPlayer = MediaPlayer.create(getApplicationContext(), item.getSound());
                        mMediaPlayer.setOnCompletionListener(mCompletionListener);

                        // Start playback.
                        mMediaPlayer.start();
                    }
                }
            }

        });
    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop()");
        super.onStop();
        releaseMediaPlayer();
    }
}
