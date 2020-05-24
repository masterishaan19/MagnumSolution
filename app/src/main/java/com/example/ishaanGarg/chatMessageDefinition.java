package com.example.ishaanGarg;

public class chatMessageDefinition {
/*Type definition:
*   Type = 1 => Text by Sender/You/Left Side
*   Type = 2 => Text by receiver/Me/Right Side
*   Type = 3 => Image by sender
*   Type = 4 => Image by receiver
*/
    private int imageId;
    private String message;
    private int type;
    public chatMessageDefinition(String message, int type){
        this.message = message;
        this.type = type;
        if(type > 2){
            this.imageId = Integer.parseInt(message);
        }
    }
    public chatMessageDefinition(int imageId, int type){
        this.type = type;
        this.imageId = imageId;
    }
    public int getImageId() {
        return this.imageId;
    }
    public String getMessage() {
        return message;
    }
    public int getType() {
        return type;
    }
}
