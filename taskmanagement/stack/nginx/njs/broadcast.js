async function join(r) {
    let uris = [
        "/orderProcess/rest/metadata",
        "/paymentProcess/rest/metadata"
    ]
    join_subrequests(r, uris)
}

async function join_subrequests(r, subs) {
    let results = await Promise.all(subs.map(uri => r.subrequest(uri)));

    let response = results.map(reply => JSON.parse(reply.responseText));

    r.return(200, JSON.stringify(response));
}

export default {join};