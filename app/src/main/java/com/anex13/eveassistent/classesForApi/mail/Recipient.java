package com.anex13.eveassistent.classesForApi.mail;

 import com.google.gson.annotations.Expose;
 import com.google.gson.annotations.SerializedName;

 public class Recipient {

@SerializedName("recipient_type")
@Expose
private String recipientType;
@SerializedName("recipient_id")
@Expose
private Integer recipientId;

/**
 *
 * @return
 * The recipientType
 */
public String getRecipientType() {
        return recipientType;
        }

/**
 *
 * @param recipientType
 * The recipient_type
 */
public void setRecipientType(String recipientType) {
        this.recipientType = recipientType;
        }

/**
 *
 * @return
 * The recipientId
 */
public Integer getRecipientId() {
        return recipientId;
        }

/**
 *
 * @param recipientId
 * The recipient_id
 */
public void setRecipientId(Integer recipientId) {
        this.recipientId = recipientId;
        }

        }
