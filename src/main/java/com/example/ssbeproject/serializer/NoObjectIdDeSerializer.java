package com.example.ssbeproject.serializer;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.bson.types.ObjectId;

import java.io.IOException;

public class NoObjectIdDeSerializer extends JsonDeserializer<ObjectId> {
    @Override
    public ObjectId deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        TreeNode treeNode = jsonParser.readValueAsTree();
        JsonNode oid = ((JsonNode) treeNode).get("$oid");
        if (oid != null) {
            return new ObjectId(oid.asText());
        } else {
            return new ObjectId(((JsonNode) treeNode).asText());
        }
    }
}