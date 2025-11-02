import React, { useState, useEffect } from 'react';
import './App.css';

function App() {
  const [isHovered, setIsHovered] = useState(false);

  const scrollToSection = (id) => {
    const element = document.getElementById(id);
    if (element) {
      element.scrollIntoView({ behavior: 'smooth' });
    }
  };

  const menuItems = [
    { id: 1, name: 'Espresso', price: '$3.50', image: '☕' },
    { id: 2, name: 'Cappuccino', price: '$4.50', image: '☕' },
    { id: 3, name: 'Avocado Toast', price: '$8.99', image: '🥑' },
    { id: 4, name: 'Blueberry Muffin', price: '$3.99', image: '🧁' },
    { id: 5, name: 'Caesar Salad', price: '$9.50', image: '🥗' },
    { id: 6, name: 'Chocolate Cake', price: '$5.99', image: '🍰' }
  ];

  return (
    <div style={{ fontFamily: 'Arial, sans-serif', margin: 0, padding: 0 }}>
      {/* Navigation Bar */}
      <nav id="navbar" style={{
        backgroundColor: '#2c3e50',
        padding: '1rem 2rem',
        position: 'sticky',
        top: 0,
        zIndex: 1000,
        boxShadow: '0 2px 5px rgba(0,0,0,0.1)'
      }}>
        <div style={{ display: 'flex', justifyContent: 'center', gap: '2rem' }}>
          <a id="nav-home" onClick={() => scrollToSection('home')} style={{
            color: 'white',
            textDecoration: 'none',
            cursor: 'pointer',
            fontSize: '1.1rem',
            fontWeight: '500'
          }}>Home</a>
          <a id="nav-menu" onClick={() => scrollToSection('menu')} style={{
            color: 'white',
            textDecoration: 'none',
            cursor: 'pointer',
            fontSize: '1.1rem',
            fontWeight: '500'
          }}>Menu</a>
          <a id="nav-about" onClick={() => scrollToSection('about')} style={{
            color: 'white',
            textDecoration: 'none',
            cursor: 'pointer',
            fontSize: '1.1rem',
            fontWeight: '500'
          }}>About Us</a>
          <a id="nav-contact" onClick={() => scrollToSection('contact')} style={{
            color: 'white',
            textDecoration: 'none',
            cursor: 'pointer',
            fontSize: '1.1rem',
            fontWeight: '500'
          }}>Contact</a>
        </div>
      </nav>

      {/* Hero Section */}
      <section id="home" style={{
        backgroundImage: 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)',
        color: 'white',
        padding: '8rem 2rem',
        textAlign: 'center',
        minHeight: '500px',
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'center',
        alignItems: 'center'
      }}>
        <h1 id="cafe-name" style={{
          fontFamily: 'Georgia, serif',
          fontSize: '3.5rem',
          marginBottom: '1rem',
          fontWeight: 'bold'
        }}>Brew & Bite Café</h1>
        <p id="tagline" style={{
          fontSize: '1.5rem',
          marginBottom: '2rem',
          fontStyle: 'italic'
        }}>Where Every Sip Tells a Story</p>
        <button
          id="reserve-button"
          onMouseEnter={() => setIsHovered(true)}
          onMouseLeave={() => setIsHovered(false)}
          style={{
            backgroundColor: isHovered ? '#e67e22' : '#f39c12',
            color: 'white',
            padding: '1rem 2.5rem',
            fontSize: '1.2rem',
            border: 'none',
            borderRadius: '50px',
            cursor: 'pointer',
            fontWeight: 'bold',
            transition: 'background-color 0.3s ease',
            boxShadow: '0 4px 15px rgba(0,0,0,0.2)'
          }}
        >
          Reserve Table
        </button>
      </section>

      {/* Menu Section */}
      <section id="menu" style={{
        padding: '4rem 2rem',
        backgroundColor: '#f8f9fa',
        minHeight: '400px'
      }}>
        <h2 style={{
          fontFamily: 'Georgia, serif',
          textAlign: 'center',
          fontSize: '2.5rem',
          marginBottom: '3rem',
          color: '#2c3e50'
        }}>Our Menu</h2>
        <div id="menu-container" style={{
          display: 'grid',
          gridTemplateColumns: 'repeat(auto-fit, minmax(250px, 1fr))',
          gap: '2rem',
          maxWidth: '1200px',
          margin: '0 auto'
        }}>
          {menuItems.map((item) => (
            <div key={item.id} className="menu-item" style={{
              backgroundColor: 'white',
              padding: '1.5rem',
              borderRadius: '10px',
              boxShadow: '0 4px 6px rgba(0,0,0,0.1)',
              textAlign: 'center',
              transition: 'transform 0.3s ease'
            }}>
              <div className="menu-item-image" style={{
                fontSize: '4rem',
                marginBottom: '1rem'
              }}>{item.image}</div>
              <h3 className="menu-item-name" style={{
                fontFamily: 'Georgia, serif',
                fontSize: '1.3rem',
                color: '#2c3e50',
                marginBottom: '0.5rem'
              }}>{item.name}</h3>
              <p className="menu-item-price" style={{
                fontSize: '1.2rem',
                color: '#e67e22',
                fontWeight: 'bold'
              }}>{item.price}</p>
            </div>
          ))}
        </div>
      </section>

      {/* About Us Section */}
      <section id="about" style={{
        padding: '4rem 2rem',
        backgroundColor: 'white',
        minHeight: '300px'
      }}>
        <h2 style={{
          fontFamily: 'Georgia, serif',
          textAlign: 'center',
          fontSize: '2.5rem',
          marginBottom: '2rem',
          color: '#2c3e50'
        }}>About Us</h2>
        <div id="about-text" style={{
          maxWidth: '800px',
          margin: '0 auto',
          fontSize: '1.1rem',
          lineHeight: '1.8',
          color: '#555',
          textAlign: 'justify'
        }}>
          Welcome to Brew & Bite Café, where passion meets perfection in every cup and every bite.
          Established in 2024, we are a trendy urban café dedicated to serving exceptional coffee and
          delicious food in a warm, inviting atmosphere. Our expert baristas craft each beverage with
          precision, using only the finest ethically-sourced beans from around the world. From our signature
          espresso blends to our artisanal pastries and wholesome meals, every item on our menu is prepared
          with love and attention to detail. We believe in creating more than just a café – we're building
          a community space where friends gather, ideas flourish, and memories are made over great food and
          exceptional coffee.
        </div>
      </section>

      {/* Contact Section */}
      <section id="contact" style={{
        padding: '4rem 2rem',
        backgroundColor: '#ecf0f1',
        minHeight: '300px',
        textAlign: 'center'
      }}>
        <h2 style={{
          fontFamily: 'Georgia, serif',
          fontSize: '2.5rem',
          marginBottom: '2rem',
          color: '#2c3e50'
        }}>Contact Us</h2>
        <div style={{
          maxWidth: '600px',
          margin: '0 auto',
          fontSize: '1.2rem',
          color: '#555'
        }}>
          <p style={{ marginBottom: '1rem' }}>
            <strong>Email:</strong> <span id="contact-email">info@brewandbitecafe.com</span>
          </p>
          <p style={{ marginBottom: '1rem' }}>
            <strong>Phone:</strong> <span id="contact-phone">+1 (555) 123-4567</span>
          </p>
          <p style={{ marginTop: '2rem', color: '#777' }}>
            Visit us at 123 Coffee Street, Downtown City
          </p>
        </div>
      </section>

      {/* Footer */}
      <footer id="footer" style={{
        backgroundColor: '#2c3e50',
        color: 'white',
        textAlign: 'center',
        padding: '2rem',
        fontSize: '0.9rem'
      }}>
        <p id="copyright-text">© 2024 Brew & Bite Café. All rights reserved.</p>
      </footer>
    </div>
  );
}

export default App;