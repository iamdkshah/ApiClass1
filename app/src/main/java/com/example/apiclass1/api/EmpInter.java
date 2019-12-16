package com.example.apiclass1.api;

import com.example.apiclass1.models.Employee;
import com.example.apiclass1.models.Flag;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EmpInter {

    @GET("employees")
    //call- send req to api and get resp
    Call<List<Employee>> getEmployees();

    @GET("employess/{id}")
    Call<Employee>
    getEmployeeById(@Path("id") int id);

    @POST("create")
    Call<Void> addEmployee (@Body Employee employee);

    @PUT("update/{id}")
    Call<Void> updateEmployee (@Path("id") int id, @Body Employee employee);

    @DELETE("delete/{id}")
    Call<Void> deleteEmployee (@Path("id") int id, @Body Employee employee);

    @GET("singleflag/{id}")
    Call<Flag> getFlagById (@Path("id") int id);
}
