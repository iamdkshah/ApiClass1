package com.example.apiclass1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apiclass1.api.EmpInter;
import com.example.apiclass1.models.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitEmpActivity extends AppCompatActivity {

    TextView textView;
    EditText empName, empSalary, empAge;
    Button empAdd;
    EmpInter empInter;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_emp);

        textView = findViewById(R.id.empApiData);
        empAdd = findViewById(R.id.empAdd);
        empName = findViewById(R.id.empName);
        empSalary = findViewById(R.id.empSalary);
        empAge = findViewById(R.id.empAge);

        getInstance();

        empAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = empName.getText().toString();
                String  age = empAge.getText().toString();
                String salary = empSalary.getText().toString();

                Employee employee = new Employee(0, name, age, salary);
                addEmp(employee);
            }
        });

    }

    private void getInstance(){
        retrofit = new Retrofit.Builder().baseUrl("http://dummy.restapiexample.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        empInter = retrofit.create(EmpInter.class);// calling emp interface class through retrofit

    }
    private void getAllEmployees(){
        Call<List<Employee>> employeeList = empInter.getEmployees(); // call type ko list
        employeeList.enqueue(new Callback<List<Employee>>() { // new cntrl+ space
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                List<Employee> emplist = response.body(); //normal list
                for (Employee emp: emplist){
                    Log.d("name", emp.getEmployee_name());
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {

                Log.d("ApiEx", t.getMessage());
            }
        });// employee list ko data lai extract garxa
    }


    private void getEmployessById(){

        Call<Employee> employee = empInter.getEmployeeById(1);

        employee.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                Toast.makeText(RetrofitEmpActivity.this, response.body().getEmployee_name(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Log.d("ApiEx", t.getMessage());

            }
        });
    }

    private void addEmp(Employee employee){
        Call<Void> empAdd = empInter.addEmployee(employee);

        empAdd.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(RetrofitEmpActivity.this, "Added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("Ex ",t.toString());
            }
        });
    }

    private void updateEmployee(Employee employee){


    }

}

