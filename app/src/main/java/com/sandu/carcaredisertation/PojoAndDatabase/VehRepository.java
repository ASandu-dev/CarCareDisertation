package com.sandu.carcaredisertation.PojoAndDatabase;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class VehRepository {
    private VehDao vehDao;
    private LiveData<List<VehicleData>> allVehicleData;
    private LiveData<List<VehicleImage>> allImages;
    private LiveData<List<VehicleTax>> allTax;
    private LiveData<List<VehicleMot>> allMot;


    public VehRepository(Application application) {
        VehicleDatabase database = VehicleDatabase.getInstance(application);
        vehDao = database.vehDao();
        allVehicleData = vehDao.getVehicleData();
        allImages = vehDao.getVehicleImage();
        allTax = vehDao.getVehicleTax();
        allMot = vehDao.getVehicleMot();
    }

    //Insert
    public void insert(VehicleData vehicleData) {
        new InsertVehicleAsyncTask(vehDao).execute(vehicleData);
    }

    public void insertImg(VehicleImage vehicleImage) {
        new InsertImageAsyncTask(vehDao).execute(vehicleImage);
    }
    public void insertTax(VehicleTax vehicleTax) {
        new InsertTaxAsyncTask(vehDao).execute(vehicleTax);
    }
    public void insertMot(VehicleMot vehicleMot) {
        new InsertMotAsyncTask(vehDao).execute(vehicleMot);
    }


    //Update
    public void update(VehicleData vehicleData) {
        new UpdateVehicleAsyncTask(vehDao).execute(vehicleData);
    }

    //Delete
    public void deleteVehicle(VehicleData vehicleData) {
        new DeleteVehicleAsyncTask(vehDao).execute(vehicleData);
    }

    public void deleteImage(VehicleImage vehicleImage) {
        new DeleteImageAsyncTask(vehDao).execute(vehicleImage);
    }

    public void deleteTax(VehicleTax vehicleTax) {
        new DeleteTaxAsyncTask(vehDao).execute(vehicleTax);
    }
    public void deleteMot(VehicleMot vehicleMot) {
        new DeleteMotAsyncTask(vehDao).execute(vehicleMot);
    }

    //Delete All
    public void deleteAllVehicles() {
        new DeleteAllVehiclesAsyncTask(vehDao).execute();
    }

    public void deleteAllImages() {
        new DeleteAllImagesAsyncTask(vehDao).execute();
    }

    public void deleteAllITaxes() {
        new DeleteAllTaxesAsyncTask(vehDao).execute();
    }

    public void deleteAllMots() {
        new DeleteAllMotsAsyncTask(vehDao).execute();
    }

    public LiveData<List<VehicleData>> getAllVehicles() {
        return allVehicleData;
    }

    public LiveData<List<VehicleImage>> getAllImages() {
        return allImages;
    }

    public LiveData<List<VehicleTax>> getAllTax() {
        return allTax;
    }

    public LiveData<List<VehicleMot>> getAllMot() {
        return allMot;
    }


    //Async Tasks

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

    private static class InsertTaxAsyncTask extends AsyncTask<VehicleTax, Void, Void> {
        private VehDao vehDao;

        private InsertTaxAsyncTask(VehDao vehDao) {
            this.vehDao = vehDao;
        }

        @Override
        protected Void doInBackground(VehicleTax... taxes) {
            vehDao.insertTax(taxes[0]);
            return null;
        }
    }

    private static class InsertMotAsyncTask extends AsyncTask<VehicleMot, Void, Void> {
        private VehDao vehDao;

        private InsertMotAsyncTask(VehDao vehDao) {
            this.vehDao = vehDao;
        }

        @Override
        protected Void doInBackground(VehicleMot... mots) {
            vehDao.insertMot(mots[0]);
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

    private static class DeleteTaxAsyncTask extends AsyncTask<VehicleTax, Void, Void> {
        private VehDao vehDao;

        private DeleteTaxAsyncTask(VehDao vehDao) {
            this.vehDao = vehDao;
        }

        @Override
        protected Void doInBackground(VehicleTax... taxes) {
            vehDao.deleteTax(taxes[0]);
            return null;
        }
    }

    private static class DeleteMotAsyncTask extends AsyncTask<VehicleMot, Void, Void> {
        private VehDao vehDao;

        private DeleteMotAsyncTask(VehDao vehDao) {
            this.vehDao = vehDao;
        }

        @Override
        protected Void doInBackground(VehicleMot... mots) {
            vehDao.deleteMot(mots[0]);
            return null;
        }
    }

    private static class DeleteAllVehiclesAsyncTask extends AsyncTask<VehicleData, Void, Void> {
        private VehDao vehDao;

        private DeleteAllVehiclesAsyncTask(VehDao vehDao) {
            this.vehDao = vehDao;
        }

        @Override
        protected Void doInBackground(final VehicleData... params) {
            vehDao.deleteVehicle(params[0]);
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

    private static class DeleteAllTaxesAsyncTask extends AsyncTask<Void, Void, Void> {
        private VehDao vehDao;

        private DeleteAllTaxesAsyncTask(VehDao vehDao) {
            this.vehDao = vehDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            vehDao.deleteAllTaxes();
            return null;
        }
    }

    private static class DeleteAllMotsAsyncTask extends AsyncTask<Void, Void, Void> {
        private VehDao vehDao;

        private DeleteAllMotsAsyncTask(VehDao vehDao) {
            this.vehDao = vehDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            vehDao.deleteAllMots();
            return null;
        }
    }
}
