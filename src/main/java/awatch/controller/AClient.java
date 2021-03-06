package awatch.controller;

import awatch.model.Character;
import threading.ThreadManagement;

import java.io.IOException;
import java.util.HashSet;

/**
 * Access the threading and ALoader which retrieves data from the APIs
 */
public class AClient {

    private final AListener listener;

    /**
     * Initialize AListener in constructor
     * @param listener AListener
     */
    public AClient(AListener listener) {
        this.listener = listener;
    }

    /**
     * Runs a thread to make a search anime call
     * @param query anime name
     * @param nsfw nsfw allowed
     */
    public void searchAnime(String query, boolean nsfw) {
        ThreadManagement.execute(() -> {
            try {
                listener.sendSearchAnime(ALoader.loadAnime(query, nsfw));
            } catch (IOException e) { throw new RuntimeException(e); }
        });
    }

    /**
     * Runs a thread to make a search character call
     * @param query character name
     */
    public void searchCharacter(String query) {
        ThreadManagement.execute(() -> {
            try {
                listener.sendSearchCharacter(ALoader.loadCharacter(query));
            } catch (IOException e) { throw new RuntimeException(e); }
        });
    }

    /**
     * Runs a thread to make a search user call
     * @param query username
     */
    public void searchUser(String query) {
        ThreadManagement.execute(() -> {
            try {
                listener.sendSearchUser(ALoader.loadSearchUser(query));
            } catch (IOException e) { throw new RuntimeException(e); }
        });
    }

    /**
     * Runs a thread to make a get character animes call
     * @param character character
     */
    public void getCharacterAnimes(Character character) {
        ThreadManagement.execute(() -> {
            try {
                listener.sendCharacterAnimes(ALoader.loadCharacterInfo(character));
            } catch (IOException e) { throw new RuntimeException(e); }
        });
    }

    /**
     * Runs a thread to make a get character mangas call
     * @param character character
     */
    public void getCharacterMangas(Character character) {
        ThreadManagement.execute(() -> {
            try {
                listener.sendCharacterMangas(ALoader.loadCharacterInfo(character));
            } catch (IOException e) { throw new RuntimeException(e); }
        });
    }

    /**
     * Runs a thread to make a get character voice actors call
     * @param character character
     */
    public void getCharacterVoices(Character character) {
        ThreadManagement.execute(() -> {
            try {
                listener.sendCharacterVoices(ALoader.loadCharacterInfo(character));
            } catch (IOException e) { throw new RuntimeException(e); }
        });
    }

    /**
     * Runs a thread to make a random quote call
     */
    public void randomQuote() {
        ThreadManagement.execute(() -> {
            try {
                listener.sendRandomQuote(ALoader.loadQuote());
            } catch (IOException e) { throw new RuntimeException(e); }
        });
    }

    /**
     * Runs a thread to get episodes of an anime
     * @param id MAL id
     */
    public void getEpisodes(long id) {
        ThreadManagement.execute(() -> {
            try {
                listener.sendEpisodes(ALoader.loadEpisodes(id));
            } catch (IOException e) { throw new RuntimeException(e); }
        });
    }

    /**
     * Runs a thread to get characters of an anime
     * @param id MAL id
     */
    public void getCharacters(long id) {
        ThreadManagement.execute(() -> {
            try {
                listener.sendCharacterList(ALoader.loadCharacterList(id));
            } catch (IOException e) { throw new RuntimeException(e); }
        });
    }

    /**
     * Runs a thread to get news of an anime
     * @param id MAL id
     */
    public void getNews(long id) {
        ThreadManagement.execute(() -> {
            try {
                listener.sendNews(ALoader.loadNews(id));
            } catch (IOException e) { throw new RuntimeException(e); }
        });
    }

    /**
     * Runs a thread to get the statistics of an anime
     * @param id MAL id
     */
    public void getStatistics(long id) {
        ThreadManagement.execute(() -> {
            try {
                listener.sendStatistics(ALoader.loadStatistics(id));
            } catch (IOException e) { throw new RuntimeException(e); }
        });
    }

    /**
     * Runs a thread to get the themes of an anime
     * @param id MAL id
     */
    public void getThemes(long id) {
        ThreadManagement.execute(() -> {
            try {
                listener.sendThemes(ALoader.loadThemes(id));
            } catch (IOException e) { throw new RuntimeException(e); }
        });
    }

    /**
     * Runs a thread to get the recommendations for an anime
     * @param id MAL id
     */
    public void getRecommendation(long id) {
        ThreadManagement.execute(() -> {
            try {
                listener.sendRecommendation(ALoader.loadRecommendations(id));
            } catch (IOException e) { throw new RuntimeException(e); }
        });
    }

    /**
     * Runs a thread to get the top review of an anime
     * @param id MAL id
     */
    public void getReview(long id) {
        ThreadManagement.execute(() -> {
            try {
                listener.sendReview(ALoader.loadReview(id));
            } catch (IOException e) { throw new RuntimeException(e); }
        });
    }

    /**
     * Runs a thread to get the top 25 animes ranked in terms of score
     */
    public void getTop() {
        ThreadManagement.execute(() -> {
            try {
                listener.sendTopAnime(ALoader.loadTop());
            } catch (IOException e) { throw new RuntimeException(e); }
        });
    }

    /**
     * Runs a thread to get the top 25 animes ranked in terms of popularity
     */
    public void getPopular() {
        ThreadManagement.execute(() -> {
            try {
                listener.sendPopularAnime(ALoader.loadPopular());
            } catch (IOException e) { throw new RuntimeException(e); }
        });
    }

    /**
     * Runs a thread to get the latest animes of the season
     */
    public void getLatest() {
        ThreadManagement.execute(() -> {
            try {
                listener.sendLatestAnime(ALoader.loadLatest());
            } catch (IOException e) { throw new RuntimeException(e); }
        });
    }

    /**
     * Runs a thread to get a randomly chosen anime from MALs database
     * @param nsfw nsfw allowed
     */
    public void randomAnime(boolean nsfw) {
        ThreadManagement.execute(() -> {
            try {
                listener.sendRandomAnime(ALoader.loadRandomAnime(nsfw));
            } catch (IOException e) { throw new RuntimeException(e); }
        });
    }

    /**
     * Runs a thread to get a randomly chosen anime from MALs database
     * @param nsfw nsfw allowed
     */
    public void randomCharacter(boolean nsfw) {
        ThreadManagement.execute(() -> {
            try {
                listener.sendRandomCharacter(ALoader.loadRandomCharacter(nsfw));
            } catch (IOException e) { throw new RuntimeException(e); }
        });
    }

    /**
     * Runs a thread to get a randomly chosen anime related GIF
     */
    public void randomGIF() {
        ThreadManagement.execute(() -> {
            try {
                listener.sendRandomGIF(ALoader.loadGIF());
            } catch (IOException e) { throw new RuntimeException(e); }
        });
    }

    /**
     * Runs a thread to get a random trivia question about anime
     */
    public void randomTrivia(HashSet<String> available) {
        ThreadManagement.execute(() -> {
            try {
                listener.sendTrivia(ALoader.loadTrivia(available));
            } catch(IOException e) { e.printStackTrace(); throw new RuntimeException(e); }
        });
    }

}
