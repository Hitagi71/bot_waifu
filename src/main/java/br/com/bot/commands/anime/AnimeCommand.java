package br.com.bot.commands.anime;

import br.com.bot.commands.ICommand;
import br.com.bot.model.anime.Anime;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import discord4j.core.event.domain.message.MessageCreateEvent;
import okhttp3.*;

import java.io.IOException;
import java.util.Random;

public class AnimeCommand implements ICommand {

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    @Override
    public void execute(MessageCreateEvent event) {
        final String message = event.getMessage().getContent();

        String url = "https://graphql.anilist.co";
        String query = "query ($id: Int) {" +
            "Media (id: $id, type: ANIME) {" +
                "id " +
                "title {"+
                    "romaji "+
                    "english "+
                    "native "+
                "} " +
                "format {" +
                    "TV "+
                    "TV_SHORT "+
                    "MOVIE "+
                "} " +
                "coverImage {" +
                    "large "+
                "} " +
                "bannerImage " +
            "}"+
        "}";

        OkHttpClient okHttpClient = new OkHttpClient();

        Variables variables = new Variables();
        variables.setId(339);

        ApiRequest apiRequest = new ApiRequest(query, variables);

        RequestBody body = RequestBody.create(new Gson().toJson(apiRequest), JSON);

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .post(body)
                .build();

        try(Response response = okHttpClient.newCall(request).execute()) {

            String json = response.body().string();


            Gson gson = new Gson();

            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

            JsonObject media = jsonObject.get("data")
                    .getAsJsonObject()
                    .get("Media")
                    .getAsJsonObject();

            Anime anime = gson.fromJson(media.toString(), Anime.class);

            String msg = "Anime: \n" +
                    "Id: " + anime.getId() + "\n" +
                    "Nome: " + anime.getTitle().getEnglish()+ "\n" +
                    "Formato de mídia: " + anime.getFormat() + "\n" +
                    "---------- Imagem --------\n" +
                    anime.getCoverImage().getLarge();

            event.getMessage()
                    .getChannel()
                    .flatMap(channel -> channel.createMessage(msg))
                    .subscribe();

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
