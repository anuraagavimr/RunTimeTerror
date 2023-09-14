package com.code.fury.model;

import java.util.*;

	public class Address {

		private int houseNumber;
		private String line1;
		private String line2;

		//Constructor
		public Address() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Address(int houseNumber, String line1, String line2) {
			super();
			this.houseNumber = houseNumber;
			this.line1 = line1;
			this.line2 = line2;
		}
		//getters and setters
		public int getHouseNumber() {
			return houseNumber;
		}
		public void setHouseNumber(int houseNumber) {
			this.houseNumber = houseNumber;
		}
		public String getLine1() {
			return line1;
		}
		public void setLine1(String line1) {
			this.line1 = line1;
		}
		public String getLine2() {
			return line2;
		}
		public void setLine2(String line2) {
			this.line2 = line2;
		}
		@Override
		public int hashCode() {
			return Objects.hash(houseNumber, line1, line2);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Address other = (Address) obj;
			return houseNumber == other.houseNumber && Objects.equals(line1, other.line1)
					&& Objects.equals(line2, other.line2);
		}
		@Override
		public String toString() {
			return "Address [houseNumber=" + houseNumber + ", line1=" + line1 + ", line2=" + line2 + "]";
		}



	}


