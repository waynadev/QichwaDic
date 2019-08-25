package com.ocram.qichwadic.framework.di.viewmodel;

import com.ocram.qichwadic.data.datasource.DictionaryCloudDataStore;
import com.ocram.qichwadic.data.datasource.DictionaryLocalDataStore;
import com.ocram.qichwadic.data.repository.DictionaryRepositoryImpl;
import com.ocram.qichwadic.data.repository.FavoriteDataRepository;
import com.ocram.qichwadic.data.repository.SearchDataRepository;
import com.ocram.qichwadic.domain.interactor.DictionaryInteractor;
import com.ocram.qichwadic.domain.interactor.DictionaryInteractorImpl;
import com.ocram.qichwadic.domain.interactor.FavoriteInteractor;
import com.ocram.qichwadic.domain.interactor.FavoriteInteractorImpl;
import com.ocram.qichwadic.domain.interactor.SearchInteractor;
import com.ocram.qichwadic.domain.interactor.SearchInteractorImpl;
import com.ocram.qichwadic.domain.repository.DictionaryRepository;
import com.ocram.qichwadic.domain.repository.FavoriteRepository;
import com.ocram.qichwadic.domain.repository.SearchRepository;
import com.ocram.qichwadic.framework.dao.DictionaryDao;
import com.ocram.qichwadic.framework.dao.FavoriteDao;
import com.ocram.qichwadic.framework.dao.SearchDao;
import com.ocram.qichwadic.framework.net.client.RetrofitClient;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchModule {

    @Provides @ViewModelScope
    static SearchInteractor provideSearchInteractor(SearchDataRepository searchRepository, DictionaryRepository dictionaryRepository) {
        return new SearchInteractorImpl(searchRepository, dictionaryRepository);
    }

    @Provides @ViewModelScope
    static SearchRepository provideSearchRepository(SearchDao searchDao) {
        return new SearchDataRepository(searchDao);
    }

    @Provides @ViewModelScope
    static FavoriteInteractor provideFavoriteInteractor(FavoriteRepository favoriteRepository) {
        return new FavoriteInteractorImpl(favoriteRepository);
    }

    @Provides @ViewModelScope
    static FavoriteRepository provideFavoriteRepository(FavoriteDao favoriteDao) {
        return new FavoriteDataRepository(favoriteDao);
    }

    @Provides
    @ViewModelScope
    static DictionaryInteractor provideDictionaryInteractor(DictionaryRepository dictionaryRepository) {
        return new DictionaryInteractorImpl(dictionaryRepository);
    }

    @Provides
    @ViewModelScope
    static DictionaryRepository provideDictionaryRepository(DictionaryRepositoryImpl.LocalDataStore localDataStore, DictionaryRepositoryImpl.CloudDataStore cloudDataStore) {
        return new DictionaryRepositoryImpl(localDataStore, cloudDataStore);
    }

    @Provides
    @ViewModelScope
    static DictionaryRepositoryImpl.LocalDataStore provideLocalDataStore(DictionaryDao dictionaryDao) {
        return new DictionaryLocalDataStore(dictionaryDao);
    }

    @Provides
    @ViewModelScope
    static DictionaryRepositoryImpl.CloudDataStore provideCloudDataStore(RetrofitClient retrofitClient) {
        return new DictionaryCloudDataStore(retrofitClient);
    }
}
