package com.example.model;



public class Employee {
	 private int id;
	    private String name;
	    private String address;
	    protected String contact;
	    protected String desgn;
	 
	    public Employee() {
	    }
	    public Employee(int id) {
	    	this.id = id;
	    }
	   
	 
	  
	     
	    public Employee(String name,String contact, String address, String desgn) {
	    
	        this.name = name;
	        this.contact = contact;
	        this.address = address;
	        this.desgn = desgn;
	    }
	    public Employee(int id,String name,String contact, String address, String desgn) {
	    	this.id = id;
	        this.name = name;
	        this.contact = contact;
	        this.address = address;
	        this.desgn = desgn;
	    }
	    public int getId() {
	        return id;
	    }
	 
	    public void setId(int id) {
	        this.id = id;
	    }
	 
	    public String getName() {
	        return name;
	    }
	 
	    public void setName(String name) {
	    	 this.name = name;
	    }
	 
	    public String getAddress() {
	        return address;
	    }
	 
	    public void setAddress(String address) {
	    	   this.address = address;
	    }
	 
	    public String getContact() {
	        return contact;
	    }
	 
	    public void setContact(String contact) {
	    	 this.contact = contact;
	    }
	    public String getDesgn() {
	        return desgn;
	    }
	 
	    public void setDesgn(String desgn) {
	    	   this.desgn = desgn;
	    }
	 
}
