import { useQuery } from 'react-query';
import axios from 'axios';

   
const fetchData = () =>{

    return axios.get('https://jsonplaceholder.typicode.com/posts')
} 

export const SuperHeroesPage = () => {
 const { isLoading, error, data } = useQuery('super-hero', fetchData);

 if (isLoading) {
   return <h2>Loading...</h2>;
 }

 if (error) {
   return <h2>An error occurred: {error.message}</h2>;
 }

 return (
   <>
     <div>
       <h2>Super Heroes Page</h2>
       {data.data.map((post) => (
         <div key={post.id}>{post.title}</div>
       ))}
     </div>
   </>
 );
};



