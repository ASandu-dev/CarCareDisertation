package com.sandu.carcaredisertation.PojoAndDatabase;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class VehRepository {
    private VehDao vehDao;
    private LiveData<List<VehicleData>> allVehicleData;
    private LiveData<List<VehicleImage>> allImages;

    public VehRepository(Application application){
        VehicleDatabase database = VehicleDatabase.getInstance(application);
        vehDao = database.vehDao();
        allVehicleData = vehDao.getVehicleData();
        allImages = vehDao.getVehicleImage();
    }

    public void insert(VehicleData vehicleData) {
        new InsertVehicleAsyncTask(vehDao).execute(vehicleData);
    }

    public void insertImg(VehicleImage vehicleImage) {
        new InsertImageAsyncTask(vehDao).execute(vehicleImage);
    }

    public void update(VehicleData vehicleData) {
        new UpdateVehicleAsyncTask(vehDao).execute(vehicleData);
    }

    public void updateImg(VehicleImage vehicleImage) {
        new UpdateImageAsyncTask(vehDao).execute(vehicleImage);
    }

    public void delete(VehicleData vehicleData) {
        new DeleteVehicleAsyncTask(vehDao).execute(vehicleData);
    }

    public void deleteImg(VehicleImage vehicleImage) {
        new DeleteImageAsyncTask(vehDao).execute(vehicleImage);
    }

    public void deleteAllVehicles() {
        new DeleteAllVehiclesAsyncTask(vehDao).execute();
    }

    public void deleteAllImages() {
        new DeleteAllImagesAsyncTask(vehDao).execute();
    }
    public LiveData<List<VehicleData>> getAllVehicles() {
        return allVehicleData;
    }

    public LiveData<List<VehicleImage>> getAllImages() {
        return allImages;
    }
    private static class InsertVehicleAsyncTask extends AsyncTask<VehicleData, Void, Void> {
        private VehDao vehDao;
        private InsertVehicleAsyncTask(VehDao vehDao) {
            this.vehDao = vehDao;
        }
        @Override
        protected Void doInBackground(VehicleData... vehicles) {
            vehDao.insertVehicle(vehicles[0]);
            return null;
        }
    }

    private static class InsertImageAsyncTask extends AsyncTask<VehicleImage, Void, Void> {
        private VehDao vehDao;
        private InsertImageAsyncTask(VehDao vehDao) {
            this.vehDao = vehDao;
        }
        @Override
        protected Void doInBackground(VehicleImage... images) {
            vehDao.insertImage(images[0]);
            return null;
        }
    }

    private static class UpdateVehicleAsyncTask extends AsyncTask<VehicleData, Void, Void> {
        private VehDao vehDao;
        private UpdateVehicleAsyncTask(VehDao vehDao) {
            this.vehDao = vehDao;
        }
        @Override
        protected Void doInBackground(VehicleData... notes) {
            vehDao.updateVehicle(notes[0]);
            return null;
        }
    }

    private static class UpdateImageAsyncTask extends AsyncTask<VehicleImage, Void, Void> {
        private VehDao vehDao;
        private UpdateImageAsyncTask(VehDao vehDao) {
            this.vehDao = vehDao;
        }
        @Override
        protected Void doInBackground(VehicleImage... images) {
            vehDao.updateImage(images[0]);
            return null;
        }
    }

    private static class DeleteVehicleAsyncTask extends AsyncTask<VehicleData, Void, Void> {
        private VehDao vehDao;
        private DeleteVehicleAsyncTask(VehDao vehDao) {
            this.vehDao = vehDao;
        }
        @Override
        protected Void doInBackground(VehicleData... notes) {
            vehDao.deleteVehicle(notes[0]);
            return null;
        }
    }

    private static class DeleteImageAsyncTask extends AsyncTask<VehicleImage, Void, Void> {
        private VehDao vehDao;
        private DeleteImageAsyncTask(VehDao vehDao) {
            this.vehDao = vehDao;
        }
        @Override
        protected Void doInBackground(VehicleImage... images) {
            vehDao.deleteImage(images[0]);
            return null;
        }
    }

    private static class DeleteAllVehiclesAsyncTask extends AsyncTask<Void, Void, Void> {
        private VehDao vehDao;
        private DeleteAllVehiclesAsyncTask(VehDao vehDao) {
            this.vehDao = vehDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            vehDao.deleteAllVehicles();
            return null;
        }
    }
    private static class DeleteAllImagesAsyncTask extends AsyncTask<Void, Void, Void> {
        private VehDao vehDao;
        private DeleteAllImagesAsyncTask(VehDao vehDao) {
            this.vehDao = vehDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            vehDao.deleteAllImages();
            return null;
        }
    }
}
