package dae.mn.springbootgraphqlengframework.security;

import org.springframework.stereotype.Service;
import sh.ory.keto.ApiClient;
import sh.ory.keto.ApiException;
import sh.ory.keto.api.ReadApi;
import sh.ory.keto.api.WriteApi;
import sh.ory.keto.model.ExpandTree;
import sh.ory.keto.model.GetCheckResponse;
import sh.ory.keto.model.RelationQuery;

@Service
public class KetoService {

    private final ReadApi readInstance;
    private final WriteApi writeInstance;

    public KetoService() {
        readInstance = new ReadApi(new ApiClient().setBasePath("http://localhost:4466"));
        writeInstance = new WriteApi(new ApiClient().setBasePath("http://localhost:4467"));
    }

    public ExpandTree expand(String namespace, String object, String relation, long maxDepth) {
        try {
            return readInstance.getExpand(namespace, object, relation, maxDepth);
        } catch (ApiException e) {
            System.out.println(e.getResponseBody());
            return null;
        }
    }

    public GetCheckResponse check(String namespace, String object, String relation, String subjectId) {
        try {
            return readInstance.getCheck(namespace, object, relation, subjectId, null, null, null);
        } catch (ApiException e) {
            System.out.println(e.getResponseBody());
            return null;
        }
    }

    public RelationQuery createRelation(String namespace, String object, String relation, String subjectId) {
        try {
            RelationQuery query = new RelationQuery();
            query.setNamespace(namespace);
            query.setObject(object);
            query.setRelation(relation);
            query.setSubjectId(subjectId);

            return writeInstance.createRelationTuple(query);

        } catch (ApiException e) {
            System.out.println(e.getResponseBody());
            return null;
        }
    }

    public void deleteRelation(String namespace, String object, String relation, String subjectId) {
        try {
            writeInstance.deleteRelationTuple(namespace, object, relation, subjectId, null, null, null);
        } catch (ApiException e) {
            System.out.println(e.getResponseBody());
        }
    }
}
