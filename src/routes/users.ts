import { Router } from 'https://deno.land/x/oak/mod.ts';

import userController from '../controllers/users.ts';
import clientController from '../controllers/clients.ts';
import closeoutController from '../controllers/closeouts.ts';
import companyController from '../controllers/company.ts';
import agendaController from '../controllers/agenda.ts';

import authMiddleware from '../middlewares/authMiddleware.ts';

const router = new Router();

// Hacer login
router.post('/user/login', userController.loginHandler);

// Obtener lista de clientes
router.post('/user/clients', authMiddleware, clientController.getClientList);

// Obtener lista de compañias
router.post('/user/companies', authMiddleware, companyController.getCompanyList);

//Agregar venta a bd
router.post('/user/sale', authMiddleware, closeoutController.addCloseout);

// Agendar una venta
router.post('/user/shedule', authMiddleware, agendaController.addSchedule);

// Obtener Agenda del usuario
router.get('/user/agenda', authMiddleware, agendaController.getAgenda);

// Obtener un pedido agendado
router.get('/user/agenda/schedule/:id', authMiddleware, agendaController.getSchedule);

// Obtener un cliente
router.get('/user/client/:id', authMiddleware, clientController.getClient);

// Obtener una compañia
router.get('/user/company/:id', authMiddleware, companyController.getCompany);

export default router;
