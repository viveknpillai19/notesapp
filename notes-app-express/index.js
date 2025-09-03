const express = require('express');
const app = express();
const port = 3000;

// Middleware to parse JSON bodies
app.use(express.json());

// Dummy /login route
app.post('/login', (req, res) => {
  // In a real app, you would check a username and password here
  res.json({ message: 'Login successful (dummy response)' });
});

// Dummy /addNote route
app.post('/addNote', (req, res) => {
  const note = req.body;
  console.log('Received note:', note);
  res.json({ status: 'success', receivedNote: note });
});

app.listen(port, () => {
  console.log(`Server is listening at http://localhost:${port}`);
});