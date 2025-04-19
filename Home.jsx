import { useState , useEffect} from 'react';

function FilterableProductTable({ products }) {
  const [filterText, setFilterText] = useState(''); //added now
  const [inStockOnly, setInStockOnly] = useState(false); 
  return (
    <div>
      <SearchBar 
        filterText={filterText} 
        inStockOnly={inStockOnly}
        onFilterTextChange={setFilterText}
        onInStockOnlyChange={setInStockOnly} />
      <ProductTable products={products}
      filterText={filterText} 
      inStockOnly={inStockOnly}  />
    </div>
  );
}
function ProductCategoryRow({ category }) {
  return (
    <tr>
      <th colSpan="2">
        {category}
      </th>
    </tr>
  );
}

function ProductRow({ product }) {
  const name = product.stocked ? product.name :
    <span style={{ color: 'red' }}>
      {product.name}
    </span>;

  return (
    <tr>
      <td>{name}</td>
      <td>{product.price}</td>
    </tr>
  );
}

function ProductTable({ products, filterText, inStockOnly }) {
  const rows = [];
  let lastCategory = null;

  products.forEach((product) => {
    if (product.name.toLowerCase().indexOf(filterText.toLowerCase()) === -1) {
      return;
    }
    if (inStockOnly && !product.stocked) {
      return;
    }
    if (product.category !== lastCategory) {
      rows.push(
        <ProductCategoryRow
          category={product.category}
          key={product.category} />
      );
    }
    rows.push(
      <ProductRow
        product={product}
        key={product.name} />
    );
    lastCategory = product.category;
  });

  return (
    <table>
      <thead>
        <tr>
          <th>Company</th>
          <th>Interns</th>
        </tr>
      </thead>
      <tbody>{rows}</tbody>
    </table>
  );
}


function SearchBar({ filterText, inStockOnly, onFilterTextChange,
  onInStockOnlyChange }) {
  return (
    <form>
      <input 
        type="text" 
        value={filterText} 
        placeholder="Search..."
        onChange={(e) => onFilterTextChange(e.target.value)}/>
      <label>
        <input 
          type="checkbox" 
          checked={inStockOnly} 
          onChange={(e) => onInStockOnlyChange(e.target.checked)}
          />
        {' '}
        Only show pune companies
      </label>
    </form>
  );
}




function Home() { 
  const [products, setProducts] = useState([]);
   useEffect(() => { 
    fetch('http://localhost:8080/tnpbackend/companies') .then(response => response.json()) .then(data =>
       { const formattedData = data.map(company =>
         ({ category: `Stipend:${company.stipend}`,
           price: company.price, stocked: company.stocked,
            name: company.name })); setProducts(formattedData);
           }); }, []); 
           return <FilterableProductTable products={products} />; 
          }
export default Home
