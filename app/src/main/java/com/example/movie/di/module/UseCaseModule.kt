package com.example.movie.di.module

import com.example.movie.di.scope.UseCaseScope
import com.example.movie.repository.MovieRepository
import com.example.movie.use_case.FindAndShowMovieByIdUseCase
import com.example.movie.use_case.FindAndShowMovieByIdUseCaseImpl
import com.example.movie.use_case.MovieUseCase
import com.example.movie.use_case.MovieUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @UseCaseScope
    @Provides
    internal fun providesMovieUseCase(repository: MovieRepository): MovieUseCase {
        return MovieUseCaseImpl(repository)
    }

    @UseCaseScope
    @Provides
    internal fun providesFindAndShowMovieByIdUseCase(repository: MovieRepository): FindAndShowMovieByIdUseCase {
        return FindAndShowMovieByIdUseCaseImpl(repository)
    }
}