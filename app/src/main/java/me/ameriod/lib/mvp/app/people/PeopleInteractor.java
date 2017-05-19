package me.ameriod.lib.mvp.app.people;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import java.net.URL;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import me.ameriod.lib.mvp.app.api.SwapiClient;
import me.ameriod.lib.mvp.app.models.PeopleResponse;

public class PeopleInteractor implements PeopleContract.Interactor {

    @NonNull
    private final SwapiClient client;

    @VisibleForTesting
    public PeopleInteractor(@NonNull SwapiClient client) {
        this.client = client;
    }

    public PeopleInteractor() {
        this(new SwapiClient());
    }

    @Override
    public Observable<PeopleResponse> getInitialPersonList() {
        return client.getPeople();
    }

    @Override
    public Observable<PeopleResponse> getPagedPersonList(@NonNull String nextUrl) {
        return Observable.just(nextUrl)
                .map(new Function<String, String>() {
                    @Override
                    public String apply(@NonNull String urlStr) throws Exception {
                        URL url = new URL(urlStr);
                        Map<String, String> queryPairs = new LinkedHashMap<>();
                        String query = url.getQuery();
                        String[] pairs = query.split("&");
                        for (String pair : pairs) {
                            int idx = pair.indexOf("=");
                            queryPairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"),
                                    URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
                        }
                        return queryPairs.get("page");
                    }
                })
                .flatMap(new Function<String, ObservableSource<PeopleResponse>>() {
                    @Override
                    public ObservableSource<PeopleResponse> apply(@NonNull String page) throws
                            Exception {
                        return client.getNextPeople(page);
                    }
                });
    }
}
