import { Application, Context } from 'https://deno.land/x/oak/mod.ts';

import users from './src/routes/users.ts';

const DEFAULT_PORT = 8080;
const app = new Application();

// Loggin
app.use(async (ctx: Context, next: Function) => {
	console.log(`HTTP ${ ctx.request.method } on ${ ctx.request.url }`);
	await next();
});

// Setting user routes
app.use(users.allowedMethods());
app.use(users.routes());

await app.listen({ port: DEFAULT_PORT });
