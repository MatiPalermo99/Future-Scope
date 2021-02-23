package com.example.future_scope.room;

import android.app.Application;
import android.os.AsyncTask;
import com.example.future_scope.model.Review;
import java.util.List;

public class AppRepository{
    private ReviewDAORoom reviewDAORoom;
    private OnResultCallback callback;

    public AppRepository(Application application, OnResultCallback context){
        AppDatabase db = AppDatabase.getInstance(application);
        reviewDAORoom = db.reviewDao();
        callback = context;
    }

    public void insertarReview(final Review review){
        new CrearReview().execute(review);
    }

    public void borrarReview(final Review review){
        new BorrarReview().execute(review);
    }

    public void buscarReviews() {
        new BuscarReviews().execute();
    }

    public interface OnResultCallback<T> {
        void onResultBusqueda(List<T> result);
    }

    private class BuscarReviews extends AsyncTask<Void, Void, List<Review>> {

        @Override
        protected List<Review> doInBackground(Void... Void) {
            List<Review> reviews = reviewDAORoom.buscarTodos();
            return reviews;
        }

        @Override
        protected void onPostExecute(List<Review> reviews) {
            super.onPostExecute(reviews);
            callback.onResultBusqueda(reviews);
        }
    }

    private class CrearReview extends AsyncTask<Review,Void,Void> {
        @Override
        protected Void doInBackground(Review... reviews) {
            reviewDAORoom.insertar(reviews[0]);
            return null;
        }
    }

    private class BorrarReview extends AsyncTask<Review,Void,Void> {
        @Override
        protected Void doInBackground(Review... reviews) {
            reviewDAORoom.borrar(reviews[0]);
            return null;
        }
    }
}