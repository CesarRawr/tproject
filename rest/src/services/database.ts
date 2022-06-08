import { MongoClient } from "https://deno.land/x/mongo@v0.29.4/mod.ts";

const client = new MongoClient();

await client.connect("mongodb+srv://Cesar:Lassoteveo10@cluster0.cgsce.mongodb.net/acorndb?authMechanism=SCRAM-SHA-1");

const db = client.database("acorndb");

export default db;