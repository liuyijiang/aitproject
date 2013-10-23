package com.ait.mongodb.database.constant;

public enum AitFileTypeConstant {

	FILE_TYPE_VOICE_MP3(){
		public String getString() {
			return ".mp3";
		}
	},FILE_TYPE_IMAGE_PNG(){
		public String getString() {
			return ".png";
		}
	},FILE_TYPE_IMAGE_GIF(){
		public String getString() {
			return ".gif";
		}
	},FILE_TYPE_IMAGE_JPG(){
		public String getString() {
			return ".jpg";
		}
	},FILE_TYPE_PDF(){
		public String getString() {
			return ".pdf";
		}
	},FILE_TYPE_XLS(){
		public String getString() {
			return ".xls";
		}
	},FILE_IMAGE_SIZE_LX(){
		public String getString() {
			return "_lx";
		}
	},FILE_IMAGE_SIZE_SM(){
		public String getString() {
			return "._sm";
		}
	};
	
	public abstract String getString();
	
}
