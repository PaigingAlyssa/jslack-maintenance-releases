package test_locally.api.audit;

import com.github.seratch.jslack.api.audit.response.ActionsResponse;
import com.github.seratch.jslack.api.audit.response.LogsResponse;
import com.github.seratch.jslack.api.audit.response.SchemasResponse;
import com.github.seratch.jslack.common.json.GsonFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import test_locally.api.util.FileReader;

import static test_locally.api.util.FieldVerification.verifyIfAllGettersReturnNonNull;
import static test_locally.api.util.FieldVerification.verifyIfAllGettersReturnNonNullRecursively;

@Slf4j
public class FieldsTest {

    private FileReader reader = new FileReader("../json-logs/samples/audit");

    @Test
    public void actions() throws Exception {
        String json = reader.readWholeAsString("/v1/actions.json");
        ActionsResponse obj = GsonFactory.createSnakeCase().fromJson(json, ActionsResponse.class);
        verifyIfAllGettersReturnNonNull(obj);
        verifyIfAllGettersReturnNonNullRecursively(obj.getActions(), "getWorkflowBuilder");
    }

    @Test
    public void logs() throws Exception {
        String json = reader.readWholeAsString("/v1/logs.json");
        LogsResponse obj = GsonFactory.createSnakeCase().fromJson(json, LogsResponse.class);
        verifyIfAllGettersReturnNonNull(obj);
        verifyIfAllGettersReturnNonNullRecursively(obj.getResponseMetadata(), "getMessages");
        verifyIfAllGettersReturnNonNull(obj.getEntries().get(0), "getDetails");
        verifyIfAllGettersReturnNonNullRecursively(obj.getEntries().get(0).getActor());
        verifyIfAllGettersReturnNonNullRecursively(obj.getEntries().get(0).getContext());
        verifyIfAllGettersReturnNonNullRecursively(obj.getEntries().get(0).getEntity(),
                "getApp",
                "getEnterprise",
                "getFile",
                "getUsergroup",
                "getChannel",
                "getWorkspace");
    }

    @Test
    public void schemas() throws Exception {
        String json = reader.readWholeAsString("/v1/schemas.json");
        SchemasResponse obj = GsonFactory.createSnakeCase().fromJson(json, SchemasResponse.class);
        verifyIfAllGettersReturnNonNull(obj);
        verifyIfAllGettersReturnNonNullRecursively(obj.getSchemas().get(0), "getWorkflow");
    }
}
